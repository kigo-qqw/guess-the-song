package ru.guess_the_song.server.game.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.repository.PlayerRepository;

public class GameRunnerFactoryImpl implements GameRunnerFactory {
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;
    private final GameRepository gameRepository;

    public GameRunnerFactoryImpl(SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper, PlayerToPlayerDtoMapper playerToPlayerDtoMapper, GameRepository gameRepository) {
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public GameRunner createGameRunner(Game game) {
        return new GameRunnerImpl(game, this.songEntryToSongEntryDtoMapper, this.playerToPlayerDtoMapper, this.gameRepository);
    }
}
