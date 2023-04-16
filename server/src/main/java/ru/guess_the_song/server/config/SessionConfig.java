package ru.guess_the_song.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.server.controller.CreateUserController;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.dispatcher.impl.DispatcherImpl;
import ru.guess_the_song.server.net.SessionFactory;
import ru.guess_the_song.server.net.impl.SessionFactoryImpl;

@Slf4j
@Configuration
@Import(UserConfig.class)
public class SessionConfig {
    private final Dispatcher dispatcher;
    private final SessionFactory sessionFactory;

    public SessionConfig(CreateUserController createUserController) {
        this.dispatcher = new DispatcherImpl();
        this.dispatcher.use(CreateUserDto.class, createUserController);

        this.sessionFactory = new SessionFactoryImpl(this.dispatcher);
    }

    @Bean
    public Dispatcher dispatcher() {
        return this.dispatcher;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return this.sessionFactory;
    }
}
