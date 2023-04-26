package ru.guess_the_song.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.controller.JoinGameController;
import ru.guess_the_song.server.controller.impl.CreateGameControllerImpl;
import ru.guess_the_song.server.controller.impl.JoinGameControllerImpl;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.mapper.impl.GameToGameDtoMapperImpl;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.repository.impl.GameRepositoryImpl;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.impl.GameServiceImpl;

@Configuration
@Import({UserConfig.class, MusicPackConfig.class, PlayerConfig.class})
public class GameConfig {
    private final UserConfig userConfig;
    private final MusicPackConfig musicPackConfig;
    private final PlayerConfig playerConfig;

    public GameConfig(UserConfig userConfig, MusicPackConfig musicPackConfig, PlayerConfig playerConfig) {
        this.userConfig = userConfig;
        this.musicPackConfig = musicPackConfig;
        this.playerConfig = playerConfig;
    }

    @Bean
    public CreateGameController createGameController() {
        return new CreateGameControllerImpl(
                gameService(),
                this.userConfig.userService(),
                this.musicPackConfig.musicPackService(),
                gameToGameDtoMapper(),
                this.userConfig.userToUserDtoMapper(),
                this.musicPackConfig.musicPackWithCorrectAnswersDtoToMusicPackMapper(),
                this.musicPackConfig.musicPackToMusicPackDtoMapper()
        );
    }

    @Bean
    public JoinGameController joinGameController() {
        return new JoinGameControllerImpl(gameService(), this.userConfig.userDtoToUserMapper());
    }

    @Bean
    public GameService gameService() {
        return new GameServiceImpl(gameRepository(), this.playerConfig.playerService());
    }

    @Bean
    public GameRepository gameRepository() {
        return new GameRepositoryImpl();
    }

    @Bean
    public GameToGameDtoMapper gameToGameDtoMapper() {
        return new GameToGameDtoMapperImpl(
                this.userConfig.userToUserDtoMapper(),
                this.musicPackConfig.musicPackToMusicPackDtoMapper()
        );
    }
}
