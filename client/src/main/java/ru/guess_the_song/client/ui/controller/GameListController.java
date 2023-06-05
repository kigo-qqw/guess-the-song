package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class GameListController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-list-view.fxml";
    public static final String TITLE = "GAME LIST";
    @FXML
    private Button createNewGameButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.createNewGameButton.setOnAction(event -> {
            this.openDialogWindow(CreateGameController.class);
        });
    }
}
