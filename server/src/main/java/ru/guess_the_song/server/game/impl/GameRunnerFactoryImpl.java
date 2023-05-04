package ru.guess_the_song.server.game.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;

public class GameRunnerFactoryImpl implements GameRunnerFactory {
    @Override
    public GameRunner createGameRunner(Game game) {
        return new GameRunnerImpl(game);
    }
}
