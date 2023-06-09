package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.net.SessionFactory;
import ru.guess_the_song.server.service.GameService;

import java.io.IOException;
import java.net.Socket;

@Slf4j
@Component
public class SessionFactoryImpl implements SessionFactory {
    private final Dispatcher dispatcher;
    private final GameService gameService;

    public SessionFactoryImpl(Dispatcher dispatcher, GameService gameService) {
        this.dispatcher = dispatcher;
        this.gameService = gameService;
    }

    @Override
    public Session createSession(Socket socket) throws IOException {
        return new SessionImpl(socket, this.dispatcher, this.gameService);
    }
}
