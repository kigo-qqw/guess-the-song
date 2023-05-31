package ru.guess_the_song.client.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.guess_the_song.client.repository.GameRepository;
import ru.guess_the_song.client.repository.UserRepository;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.client.ui.controller.GameListController;
import ru.guess_the_song.client.ui.controller.LoginController;
import ru.guess_the_song.client.ui.controller.SplashScreenController;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    private ConnectionService connectionService;
    private UserService userService;
    private GameService gameService;

    @Bean
    public SplashScreenController splashScreenController() {
        return new SplashScreenController();
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(userService());
    }

    @Bean
    public GameListController gameListController() {
        return new GameListController(userService(), gameService());
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
    public ConnectionService connectionService() {
        if (this.connectionService == null)
            this.connectionService = new ConnectionService();
        return this.connectionService;
    }
}
