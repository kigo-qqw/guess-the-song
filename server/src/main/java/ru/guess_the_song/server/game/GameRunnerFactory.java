package ru.guess_the_song.server.game;

import ru.guess_the_song.server.entity.Game;

public interface GameRunnerFactory {
    GameRunner createGameRunner(Game game);
}
