package ru.guess_the_song.server.net;

import java.net.Socket;

public interface Session extends Runnable {
    void send(Object object);

    Socket getSocket();
}
