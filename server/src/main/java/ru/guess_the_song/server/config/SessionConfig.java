package ru.guess_the_song.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.controller.*;
import ru.guess_the_song.server.controller.impl.GiveAnswerControllerImpl;
import ru.guess_the_song.server.dispatcher.Dispatcher;
import ru.guess_the_song.server.dispatcher.impl.DispatcherImpl;
import ru.guess_the_song.server.net.SessionFactory;
import ru.guess_the_song.server.net.impl.SessionFactoryImpl;

@Slf4j
@Configuration
@Import({UserConfig.class, GameConfig.class})
public class SessionConfig {
    private final Dispatcher dispatcher;
    private final SessionFactory sessionFactory;
    private final GameConfig gameConfig;

    public SessionConfig(
            GameConfig gameConfig,
            CreateUserController createUserController,
            CreateGameController createGameController,
            JoinGameController joinGameController,
            StartGameController startGameController,
            GetActiveGamesController getActiveGamesController,
            GetUserController getUserController,
            GetPlayerController getPlayerController,
            GiveAnswerController giveAnswerController,
            LeaveGameController leaveGameController
    ) {
        this.gameConfig = gameConfig;
        this.dispatcher = new DispatcherImpl();
        this.dispatcher.use(CreateUserDto.class, createUserController);
        this.dispatcher.use(CreateGameDto.class, createGameController);
        this.dispatcher.use(JoinGameDto.class, joinGameController);
        this.dispatcher.use(StartGameDto.class, startGameController);
        this.dispatcher.use(GetActiveGamesDto.class, getActiveGamesController);
        this.dispatcher.use(GetUserDto.class, getUserController);
        this.dispatcher.use(GetPlayerDto.class, getPlayerController);
        this.dispatcher.use(GiveAnswerDto.class, giveAnswerController);
        this.dispatcher.use(LeaveGameDto.class, leaveGameController);

        this.sessionFactory = new SessionFactoryImpl(this.dispatcher, this.gameConfig.gameService());
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
