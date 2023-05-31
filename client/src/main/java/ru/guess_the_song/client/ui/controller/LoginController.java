package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.UserDto;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
@Component
public class LoginController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/login-view.fxml";
    public static final String TITLE = "LOGIN";
    private final UserService userService;

    @FXML
    private TextField usernameTextField;
    @FXML
    private Button connectButton;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.connectButton.setOnAction(event -> {
                    Optional<UserDto> user = this.userService.login(this.usernameTextField.getText());
                    log.debug(String.valueOf(user));
                    if (user.isPresent()) {
                        this.changeWindow(GameListController.class);
                    }
                }
        );
    }
}
