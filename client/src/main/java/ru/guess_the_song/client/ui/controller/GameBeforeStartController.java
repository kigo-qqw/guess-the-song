package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.client.ui.components.PlayerList;
import ru.guess_the_song.core.dto.UserDto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
@Component
public class GameBeforeStartController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-before-start-view.fxml";
    public static final String TITLE = "GAME BEFORE START";
    private final PlayerRepository playerRepository;
    private final GameService gameService;
    private final UserService userService;
    @FXML
    private PlayerList playerList;
    @FXML
    private Button startGameButton;

    public GameBeforeStartController(PlayerRepository playerRepository, GameService gameService, UserService userService) {
        this.playerRepository = playerRepository;
        this.gameService = gameService;
        this.userService = userService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.playerList.setItems(this.playerRepository.getAll());

        this.startGameButton.setOnAction(event -> {
            Optional<UserDto> optionalUserDto = this.userService.get();
            if (optionalUserDto.isPresent()) {
                try {
                    this.gameService.start(optionalUserDto.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        log.debug(this.playerRepository.getAll().stream().toList().toString());
    }
}
