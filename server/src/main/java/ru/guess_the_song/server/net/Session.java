package ru.guess_the_song.server.net;

public interface Session extends Runnable {
    void send(Object object);
}
