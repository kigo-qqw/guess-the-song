package ru.guess_the_song.server.game.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.EndRoundDto;
import ru.guess_the_song.core.dto.SongEntryDto;
import ru.guess_the_song.core.dto.StartRoundDto;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.repository.GameRepository;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Component
public class GameRunnerImpl implements GameRunner {
    private final Game game;
    private final Map<Player, Session> players = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final Map<Player, Integer> answers = new TreeMap<>(Comparator.comparing(player -> player.getUser().getId()));
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final GameRepository gameRepository;

    public GameRunnerImpl(Game game, SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper, GameRepository gameRepository) {
        this.game = game;
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
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
    }

    @Override
    public void giveAnswer(Player player, int answerId) {
        this.answers.put(player, answerId);
    }
}
