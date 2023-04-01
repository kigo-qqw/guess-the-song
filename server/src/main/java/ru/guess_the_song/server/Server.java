package ru.guess_the_song.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket listener;
    private final ArrayList<Connection> activeConnections = new ArrayList<>();

    public Server(int port) {
        try {
            this.listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serveForever() throws IOException {
        while (true) {
            Socket clientSocket = this.listener.accept();
            Connection connection = new Connection(clientSocket);
            connection.start();
            this.activeConnections.add(connection);
        }
    }
}
