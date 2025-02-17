package ru.guess_the_song.server.game;

import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.net.Session;

import java.net.Socket;

public interface GameRunner extends Runnable {
    void addPlayer(Player player, Session session);

    void removePlayer(Player player);

    void giveAnswer(Player player, int answerId);

    void notifySocketClose(Socket socket);
}
