package ru.guess_the_song.server;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.impl.ServerImpl;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            log.info("starting...");
            Server server = new ServerImpl(8000);  // TODO: factory
            server.serveForever();
        } catch (IOException ignored) {
        }
    }
}
