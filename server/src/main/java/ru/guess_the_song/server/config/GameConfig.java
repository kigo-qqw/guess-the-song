package ru.guess_the_song.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.controller.impl.CreateGameControllerImpl;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.repository.impl.GameRepositoryImpl;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.impl.GameServiceImpl;

@Configuration
@Import(UserConfig.class)
public class GameConfig {
    private final UserConfig userConfig;

    public GameConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    @Bean
    public CreateGameController createGameController() {
        return new CreateGameControllerImpl(gameService(), this.userConfig.userService(), musicPackService, musicPackMapper);
    }

    @Bean
    public GameService gameService() {
        return new GameServiceImpl(gameRepository());
    }

    @Bean
    public GameRepository gameRepository() {
        return new GameRepositoryImpl();
    }
}
