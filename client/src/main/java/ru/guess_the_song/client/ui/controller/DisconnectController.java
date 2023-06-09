package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class DisconnectController extends BaseDialogController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/disconnect-view.fxml";
    public static final String TITLE = "DISCONNECT";

    @FXML
    private Button okButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("disconnect");
        this.okButton.setOnAction(event -> {
            close();
            getScreenManager().exit();
        });
    }
}
