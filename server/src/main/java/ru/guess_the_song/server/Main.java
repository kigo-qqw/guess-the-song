package ru.guess_the_song.server;

import ru.guess_the_song.server.net.Server;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(1234);
            server.serveForever();
        } catch (IOException ignored) {
        }
    }
}
