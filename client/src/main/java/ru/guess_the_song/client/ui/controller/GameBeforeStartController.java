package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.ui.components.PlayerList;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class GameBeforeStartController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-before-start-view.fxml";
    public static final String TITLE = "GAME BEFORE START";

    private final PlayerRepository playerRepository;
    @FXML
    private PlayerList playerList;

    public GameBeforeStartController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.playerList.setItems(this.playerRepository.getAll());
    }
}
