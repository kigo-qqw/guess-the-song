package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.guess_the_song.server.controller.GetPlayerController;
import ru.guess_the_song.server.controller.impl.GetPlayerControllerImpl;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.impl.PlayerToPlayerDtoMapperImpl;
import ru.guess_the_song.server.repository.PlayerRepository;
import ru.guess_the_song.server.repository.impl.PlayerRepositoryImpl;
import ru.guess_the_song.server.service.PlayerService;
import ru.guess_the_song.server.service.impl.PlayerServiceImpl;


@Configuration
public class PlayerConfig {
    private final UserConfig userConfig;

    public PlayerConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    @Bean
    public PlayerService playerService() {
        return new PlayerServiceImpl(playerRepository());
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepositoryImpl();
    }

    @Bean
    public PlayerToPlayerDtoMapper playerToPlayerDtoMapper() {
        return new PlayerToPlayerDtoMapperImpl(this.userConfig.userToUserDtoMapper());
    }

    @Bean
    public GetPlayerController getPlayerController() {
        return new GetPlayerControllerImpl(playerService(), playerToPlayerDtoMapper());
    }
}
