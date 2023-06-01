package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.UserDto;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
@Component
public class CreateGameController extends BaseDialogController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/create-game-view.fxml";
    public static final String TITLE = "CREATE GAME CONTROLLER";
    private final GameService gameService;
    private final UserService userService;

    private MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto = null;
    @FXML
    private Button musicPackMenuButton;
    @FXML
    private Button createNewGameButton;

    public CreateGameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.musicPackMenuButton.setOnAction(event -> {
            MusicPackMenuController musicPackMenuController = this.openDialogWindow(MusicPackMenuController.class);
            musicPackMenuController.result().ifPresent(mp -> this.musicPackWithCorrectAnswersDto = mp);
        });
        this.createNewGameButton.setOnAction(event -> {
            Optional<UserDto> optionalUserDto = this.userService.get();
            if (optionalUserDto.isEmpty()) {
                this.changeWindow(LoginController.class);
            } else {
                if (this.musicPackWithCorrectAnswersDto == null) return;  // FIXME: 01.06.2023 ERROR DIALOG

                this.gameService.create(
                        optionalUserDto.get(),
                        this.musicPackWithCorrectAnswersDto
                );
            }
        });
    }
}
