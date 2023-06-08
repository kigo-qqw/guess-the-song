package ru.guess_the_song.server.net.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.ServerFactory;
import ru.guess_the_song.server.net.SessionFactory;

import java.io.IOException;


@Slf4j
@Component
public class ServerFactoryImpl implements ServerFactory {
    @Value("${server.port}")
    private int port;
    private final SessionFactory sessionFactory;

    @Autowired
    public ServerFactoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Server createServer() throws IOException {
        return new ServerImpl(this.sessionFactory, this.port);
    }
}
