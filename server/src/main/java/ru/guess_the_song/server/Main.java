package ru.guess_the_song.server;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.guess_the_song.server.config.AppConfig;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.ServerFactory;

import java.io.IOException;

@Slf4j
public class Main {
    @Value("${server.port}:8000")
    private int port;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            ServerFactory serverFactory = context.getBean(ServerFactory.class);

            Server server = serverFactory.createServer();
            server.serveForever();
        } catch (IOException ignored) {
        }
    }
}
