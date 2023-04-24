package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.net.SessionFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
@Component
public class ServerImpl implements Server {
    private final SessionFactory sessionFactory;
    private final ServerSocket socket;
    private boolean isRunning = true;

    public ServerImpl(SessionFactory sessionFactory, int port) throws IOException {
        log.debug("sessionFactory: {}", sessionFactory);
        this.sessionFactory = sessionFactory;
        try {
            this.socket = new ServerSocket(port);
        } catch (IOException e) {
            log.error("Could not start server on port {}", port);
            throw e;
        }
    }

    @Override
    public void serveForever() {
        while (this.isRunning) {
            try {
                Socket clientSocket = this.socket.accept();
                Session connection = this.sessionFactory.createSession(clientSocket);
                new Thread(connection).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void shutdown() {
        this.isRunning = false;
    }
}