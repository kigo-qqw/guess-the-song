package ru.guess_the_song.server.game.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.PlayerService;

import java.net.Socket;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Slf4j
@Component
public class GameRunnerImpl implements GameRunner {
    //    @Value("${game.round.duration}")
    private final int roundLength;
    private Game game;
    private final Map<Player, Session> players = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final Map<Player, Integer> answers = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;
    private final GameToGameDtoMapper gameToGameDtoMapper;
    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameRunnerImpl(
            Game game,
            SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper,
            PlayerToPlayerDtoMapper playerToPlayerDtoMapper,
            GameToGameDtoMapper gameToGameDtoMapper,
            GameRepository gameRepository,
            int roundLength,
            PlayerService playerService) {
        this.game = game;
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
        this.gameRepository = gameRepository;
        this.roundLength = roundLength;
        this.playerService = playerService;
    }

    @Override
    public void run() {
        log.info("this.roundLength={}", this.roundLength);
        players.forEach((player, session) -> {
            session.send(StartGameNotificationDto.builder()
                    .gameDto(this.gameToGameDtoMapper.map(this.game))
                    .build());
        });

        MusicPack musicPack = this.game.getMusicPack();
        for (SongEntry songEntry : musicPack.getSongs()) {
            this.answers.clear();
            SongEntryDto songEntryDto = this.songEntryToSongEntryDtoMapper.map(songEntry);
            StartRoundDto startRoundDto = StartRoundDto.builder()
                    .gameId(game.getId())
                    .song(songEntryDto)
                    .length(this.roundLength)
                    .build();

            players.forEach((player, session) -> {
                log.info("player={}", player);
                session.send(startRoundDto);
            });
            try {
                Thread.sleep(this.roundLength);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("answers={}", this.answers);

            this.answers.forEach((player, answerId) -> {
                if (answerId.equals(songEntry.getCorrectAnswerIdx())) {
//                    player.setPoints(player.getPoints() + 1);
                    playerService.increasePoints(player, 1);
                    log.info("answerId={} correct={} player={}", answerId, songEntry.getCorrectAnswerIdx(), player);
                }
            });
            this.game = this.gameRepository.findById(this.gameRepository.save(this.game).getId()).get();
            log.info("players=" + this.game.getPlayers());

            EndRoundDto endRoundDto = EndRoundDto.builder()
                    .gameId(game.getId())
                    .answers(songEntryDto.getAnswers())
                    .correctAnswerId(songEntry.getCorrectAnswerIdx())
                    .players(this.game.getPlayers().stream().map(this.playerToPlayerDtoMapper::map).toArray(PlayerDto[]::new))
                    .build();
            players.forEach((player, session) -> {
                session.send(endRoundDto);
            });
        }
        Optional<Player> optionalPlayer = this.game.getPlayers().stream()
                .max(Comparator.comparing(Player::getPoints));

        log.info("{} won", optionalPlayer);
        if (optionalPlayer.isPresent()) {
            GameFinishedDto gameFinishedDto = GameFinishedDto.builder()
                    .game(this.gameToGameDtoMapper.map(this.game))
                    .winner(this.playerToPlayerDtoMapper.map(optionalPlayer.get()))
                    .build();

            players.forEach((player, session) -> {
                session.send(gameFinishedDto);
            });
        }
    }

    @Override
    public void addPlayer(Player player, Session session) {
        this.players.put(player, session);
        this.game.getPlayers().add(player);

        log.debug("resave");
        this.game = this.gameRepository.save(this.game);

        this.players.forEach((p, s) -> {
            if (p.equals(player)) return;
            s.send(PlayerJoinGameNotificationDto.builder().player(this.playerToPlayerDtoMapper.map(player)).build());
        });
    }

    @Override
    public void giveAnswer(Player player, int answerId) {
        this.answers.put(player, answerId);
        log.info("answers={}", this.answers);
    }

    @Override
    public void notifySocketClose(Socket socket) {
        this.players.entrySet().stream().filter(
                playerSessionEntry -> playerSessionEntry.getValue().getSocket().equals(socket)
        ).findFirst().ifPresent(
                playerSessionEntry -> this.players.remove(playerSessionEntry.getKey())
        );
        this.players.forEach((p, s) -> {
            if (s.getSocket().equals(socket))
                return;
            s.send(PlayerLeaveDto.builder()
                    .game(this.gameToGameDtoMapper.map(this.game))
                    .build());
        });
    }
}
