package ru.guess_the_song.server;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.impl.ServerImpl;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.error("DATABASE_URL: {}", System.getenv("DATABASE_URL"));
        log.error("DATABASE_USERNAME: {}", System.getenv("DATABASE_USERNAME"));
        log.error("DATABASE_PASSWORD: {}", System.getenv("DATABASE_PASSWORD"));

        try {
            log.info("starting...");
            Server server = new ServerImpl(8000);  // TODO: factory
            server.serveForever();
        } catch (IOException ignored) {
        }
    }
}
