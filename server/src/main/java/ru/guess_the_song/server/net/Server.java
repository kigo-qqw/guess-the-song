package ru.guess_the_song.server.net;

public interface Server {
    void serveForever();

    void shutdown();
}
