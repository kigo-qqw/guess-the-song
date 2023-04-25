package ru.guess_the_song.server;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.guess_the_song.server.config.AppConfig;
import ru.guess_the_song.server.net.Server;
import ru.guess_the_song.server.net.ServerFactory;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            ServerFactory serverFactory = context.getBean(ServerFactory.class);
//            int port = Integer.parseInt(System.getenv("PORT"));
            int port = 8000;
            Server server = serverFactory.createServer(port);
            server.serveForever();
        } catch (IOException ignored) {
        }
    }
}
