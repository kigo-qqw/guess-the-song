package ru.guess_the_song.server.game.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.PlayerService;

@Slf4j
@Component
public class GameRunnerFactoryImpl implements GameRunnerFactory {
    @Value("${game.round.duration}")
    private int ROUND_LENGTH;
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;
    private final GameToGameDtoMapper gameToGameDtoMapper;
    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameRunnerFactoryImpl(
            SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper,
            PlayerToPlayerDtoMapper playerToPlayerDtoMapper,
            GameToGameDtoMapper gameToGameDtoMapper,
            GameRepository gameRepository,
            PlayerService playerService) {
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @Override
    public GameRunner createGameRunner(Game game) {
        return new GameRunnerImpl(
                game,
                this.songEntryToSongEntryDtoMapper,
                this.playerToPlayerDtoMapper,
                this.gameToGameDtoMapper,
                this.gameRepository,
                ROUND_LENGTH,
                this.playerService);
    }
}
