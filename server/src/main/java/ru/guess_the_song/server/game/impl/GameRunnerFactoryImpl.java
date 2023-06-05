package ru.guess_the_song.server.game.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.repository.GameRepository;

public class GameRunnerFactoryImpl implements GameRunnerFactory {
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;
    private final GameRepository gameRepository;

    public GameRunnerFactoryImpl(SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper, GameRepository gameRepository) {
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public GameRunner createGameRunner(Game game) {
        return new GameRunnerImpl(game, this.songEntryToSongEntryDtoMapper, this.gameRepository);
    }
}
