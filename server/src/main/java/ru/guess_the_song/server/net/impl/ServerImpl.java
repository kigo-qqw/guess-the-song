package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
public class ServerImpl implements Server {

    private final ServerSocket socket;
    private boolean isRunning = true;

    public ServerImpl(int port) throws IOException {
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
                Session connection = new SessionImpl(clientSocket);  // TODO: factory
                new Thread(connection).start();
                log.info("Accept: {}", clientSocket.getPort());
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