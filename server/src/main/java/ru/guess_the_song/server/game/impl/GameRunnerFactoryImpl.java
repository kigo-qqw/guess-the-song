package ru.guess_the_song.server.game.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;
import ru.guess_the_song.server.net.Session;

import java.util.Map;

public class GameRunnerFactoryImpl implements GameRunnerFactory {
    private final SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper;

    public GameRunnerFactoryImpl(SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper) {
        this.songEntryToSongEntryDtoMapper = songEntryToSongEntryDtoMapper;
    }

    @Override
    public GameRunner createGameRunner(Game game, Map<Player, Session> players) {
        return new GameRunnerImpl(game, players, this.songEntryToSongEntryDtoMapper);
    }
}
