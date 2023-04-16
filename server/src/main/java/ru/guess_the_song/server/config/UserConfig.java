package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.guess_the_song.server.controller.CreateUserController;
import ru.guess_the_song.server.controller.impl.CreateUserControllerImpl;
import ru.guess_the_song.server.repository.UserRepository;
import ru.guess_the_song.server.repository.impl.UserRepositoryImpl;
import ru.guess_the_song.server.service.UserService;
import ru.guess_the_song.server.service.impl.UserServiceImpl;

@Configuration
public class UserConfig {
    @Bean
    public CreateUserController createUserController() {
        return new CreateUserControllerImpl(userService());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }
}
