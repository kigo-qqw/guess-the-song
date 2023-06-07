package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
@Component
public class SessionImpl implements Session {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Dispatcher dispatcher;
    private final GameService gameService;

    @Autowired
    public SessionImpl(Socket socket, Dispatcher dispatcher, GameService gameService) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.dispatcher = dispatcher;
        this.gameService = gameService;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object data = this.in.readObject();
                this.dispatcher.dispatch(this, data);
            }
        } catch (IOException e) {
            log.info("socket {} disconnected", this.socket);
            this.gameService.notifySocketClose(this.socket);
        } catch (ClassNotFoundException e) {
            log.info("socket {} sent invalid data", this.socket);
        } finally {
            try {
                this.in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void send(Object object) {
        try {
            this.out.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Socket getSocket() {
        return this.socket;
    }
}
