package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.guess_the_song.server.net.ServerFactory;
import ru.guess_the_song.server.net.SessionFactory;
import ru.guess_the_song.server.net.impl.ServerFactoryImpl;


@Configuration
@Import(SessionConfig.class)
@PropertySource("classpath:application.properties")
public class ServerConfig {
    private final ServerFactory serverFactory;

    public ServerConfig(SessionFactory sessionFactory) {
        this.serverFactory = new ServerFactoryImpl(sessionFactory);
    }

    @Bean
    public ServerFactory serverFactory() {
        return this.serverFactory;
    }
}
