package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
