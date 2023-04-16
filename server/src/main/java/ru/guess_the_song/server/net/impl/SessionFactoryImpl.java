package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.net.SessionFactory;

import java.io.IOException;
import java.net.Socket;

@Slf4j
@Component
public class SessionFactoryImpl implements SessionFactory {
    private final Dispatcher dispatcher;
    public SessionFactoryImpl(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Session createSession(Socket socket) throws IOException {
        return new SessionImpl(socket, this.dispatcher);
    }
}
