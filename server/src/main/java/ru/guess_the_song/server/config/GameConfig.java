package ru.guess_the_song.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.controller.GetActiveGamesController;
import ru.guess_the_song.server.controller.JoinGameController;
import ru.guess_the_song.server.controller.StartGameController;
import ru.guess_the_song.server.controller.impl.CreateGameControllerImpl;
import ru.guess_the_song.server.controller.impl.GetActiveGamesControllerImpl;
import ru.guess_the_song.server.controller.impl.JoinGameControllerImpl;
import ru.guess_the_song.server.controller.impl.StartGameControllerImpl;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.game.impl.GameRunnerFactoryImpl;
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
                this.musicPackConfig.musicPackWithCorrectAnswersDtoToMusicPackMapper()
        );
    }

    @Bean
    public JoinGameController joinGameController() {
        return new JoinGameControllerImpl(gameService(), this.userConfig.userService(), gameToGameDtoMapper());
    }

    @Bean
    public StartGameController startGameController() {
        return new StartGameControllerImpl(gameService(), this.userConfig.userService());
    }

    @Bean
    public GameService gameService() {
        return new GameServiceImpl(gameRepository(), gameRunnerFactory(), this.playerConfig.playerService());
    }

    @Bean
    public GameRunnerFactory gameRunnerFactory() {
        return new GameRunnerFactoryImpl(this.musicPackConfig.songEntryToSongEntryDtoMapper(), this.playerConfig.playerToPlayerDtoMapper(), gameRepository());
    }

    @Bean
    public GameRepository gameRepository() {
        return new GameRepositoryImpl();
    }

    @Bean
    public GameToGameDtoMapper gameToGameDtoMapper() {
        return new GameToGameDtoMapperImpl(this.playerConfig.playerToPlayerDtoMapper());
    }

    @Bean
    public GetActiveGamesController getActiveGamesController() {
        return new GetActiveGamesControllerImpl(this.gameService(), this.gameToGameDtoMapper());
    }
}
