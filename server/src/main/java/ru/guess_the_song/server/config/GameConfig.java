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
@Import({UserConfig.class, MusicPackConfig.class})
public class GameConfig {
    private final UserConfig userConfig;
    private final MusicPackConfig musicPackConfig;

    public GameConfig(UserConfig userConfig, MusicPackConfig musicPackConfig) {
        this.userConfig = userConfig;
        this.musicPackConfig = musicPackConfig;
    }

    @Bean
    public CreateGameController createGameController() {
        return new CreateGameControllerImpl(
                gameService(),
                this.userConfig.userService(),
                this.musicPackConfig.musicPackService(),
                this.musicPackConfig.musicPackWithCorrectAnswersDtoToMusicPackMapper()
        );
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
