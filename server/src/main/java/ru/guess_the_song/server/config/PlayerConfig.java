package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.impl.PlayerToPlayerDtoMapperImpl;
import ru.guess_the_song.server.repository.PlayerRepository;
import ru.guess_the_song.server.repository.impl.PlayerRepositoryImpl;
import ru.guess_the_song.server.service.PlayerService;
import ru.guess_the_song.server.service.impl.PlayerServiceImpl;


@Configuration
public class PlayerConfig {
    @Bean
    public PlayerService playerService() {
        return new PlayerServiceImpl(playerRepository());
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepositoryImpl();
    }

    public PlayerToPlayerDtoMapper playerToPlayerDtoMapper() {
        return new PlayerToPlayerDtoMapperImpl();
    }
}
