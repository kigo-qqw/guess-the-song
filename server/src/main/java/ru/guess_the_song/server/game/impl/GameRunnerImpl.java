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

import java.net.Socket;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Slf4j
@Component
public class GameRunnerImpl implements GameRunner {
    private final Game game;
    private final Map<Player, Session> players = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final Map<Player, Integer> answers = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;
    private final GameToGameDtoMapper gameToGameDtoMapper;
    private final GameRepository gameRepository;

    public GameRunnerImpl(Game game, SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper, PlayerToPlayerDtoMapper playerToPlayerDtoMapper, GameToGameDtoMapper gameToGameDtoMapper, GameRepository gameRepository) {
        this.game = game;
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public void run() {
        boolean run = true;
        int songIdx = 0;
        while (run) {
            log.debug("game=" + this.game);

            MusicPack musicPack = this.game.getMusicPack();
            SongEntry songEntry = musicPack.getSongs().get(songIdx);
            SongEntryDto songEntryDto = this.songEntryToSongEntryDtoMapper.map(songEntry);
            StartRoundDto startRoundDto = StartRoundDto.builder()
                    .gameId(game.getId())
                    .song(songEntryDto)
                    .length(5000) // FIXME: 04.05.2023
                    .build();

            players.forEach((player, session) -> {
                session.send(startRoundDto);
            });
            try {
                Thread.sleep(5000);  // FIXME: 04.05.2023 
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.answers.forEach((player, answerId) -> {
            });

            EndRoundDto endRoundDto = EndRoundDto.builder()
                    .gameId(game.getId())
                    .answers(songEntryDto.getAnswers())
                    .correctAnswerId(songEntry.getCorrectAnswerIdx())
                    .build();
            players.forEach((player, session) -> {
                session.send(endRoundDto);
            });
            songIdx++;
        }
    }

    @Override
    public void addPlayer(Player player, Session session) {
        this.players.put(player, session);
        this.game.getPlayers().add(player);

        log.debug("resave");
        this.gameRepository.save(this.game);

        this.players.forEach((p, s) -> {
            if (p.equals(player)) return;
            s.send(PlayerJoinGameNotificationDto.builder().player(this.playerToPlayerDtoMapper.map(player)).build());
        });
    }

    @Override
    public void giveAnswer(Player player, int answerId) {
        this.answers.put(player, answerId);
    }

    @Override
    public void notifySocketClose(Socket socket) {
        this.players.entrySet().stream().filter(
                playerSessionEntry -> playerSessionEntry.getValue().getSocket().equals(socket)
        ).findFirst().ifPresent(
                playerSessionEntry -> this.players.remove(playerSessionEntry.getKey())
        );
        this.players.forEach((p, s) -> {
//            log.info("session socket=" + s.getSocket());
//            log.info("socket=" + socket);
            if (s.getSocket().equals(socket))
                return;
            s.send(PlayerLeaveDto.builder()
                    .game(this.gameToGameDtoMapper.map(this.game))
                    .build());
        });
    }
}
