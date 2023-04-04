package ru.guess_the_song.server.net;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {
    private final ServerSocket socket;
    private boolean isRunning = true;

    public Server(int port) throws IOException {
        try {
            this.socket = new ServerSocket(port);
        } catch (IOException e) {
            log.error("Could not start server on port {}", port);
//            System.exit(1);
            throw e;
        }
    }

    public void serveForever() {
        while (this.isRunning) {
            try {
                Socket clientSocket = this.socket.accept();
            } catch (IOException e) {
                log.warn("{}");
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        this.isRunning = false;
    }

}
