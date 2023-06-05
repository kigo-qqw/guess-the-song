package ru.guess_the_song.client.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.guess_the_song.client.repository.GameRepository;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.repository.UserRepository;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.client.ui.controller.*;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    private ConnectionService connectionService;
    private UserService userService;
    private GameService gameService;

    @Bean
    public SplashScreenController splashScreenController() {
        return new SplashScreenController(connectionService());
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(userService());
    }

    @Bean
    public GameListController gameListController() {
        return new GameListController();
    }

    @Bean
    public CreateGameController createGameController() {
        return new CreateGameController(gameService(), userService());
    }

    @Bean
    public MusicPackMenuController musicPackMenuController() {
        return new MusicPackMenuController();
    }

    @Bean
    public MusicPackBuilderController musicPackBuilderController() {
        return new MusicPackBuilderController();
    }

    @Bean
    public GameBeforeStartController gameBeforeStartController() {
        return new GameBeforeStartController(playerRepository());
    }

    @Bean
    public GameService gameService() {
        if (this.gameService == null)
            this.gameService = new GameService(gameRepository());
        return this.gameService;
    }

    @Bean
    public GameRepository gameRepository() {
        return new GameRepository(connectionService());
    }

    @Bean
    public UserService userService() {
        if (this.userService == null)
            this.userService = new UserService(userRepository());
        return this.userService;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(connectionService());
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepository();
    }

    @Bean
    public ConnectionService connectionService() {
        log.debug("Connection Service Get");
        if (this.connectionService == null)
            this.connectionService = new ConnectionService();
        return this.connectionService;
    }
}
