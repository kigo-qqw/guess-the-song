package ru.guess_the_song.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
       Server server = new Server(1234);
        try {
            server.serveForever();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
