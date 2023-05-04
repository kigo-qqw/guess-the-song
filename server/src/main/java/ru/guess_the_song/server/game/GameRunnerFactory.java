package ru.guess_the_song.server.game;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.net.Session;

import java.util.Map;

public interface GameRunnerFactory {
    GameRunner createGameRunner(Game game, Map<Player, Session> players);
}
