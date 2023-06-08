package ru.guess_the_song.client.ui.controller;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.client.ui.ScreenManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class SplashScreenController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/splash-screen-view.fxml";
    public static final String TITLE = "SPLASH SCREEN";

    private final ConnectionService connectionService;

    @FXML
    private ProgressBar progressBar;

    public SplashScreenController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.connectionService.connect("localhost", 8000);
//            this.connectionService.setScreenManager(getScreenManager());
            log.debug("set connectionService=" + this.connectionService);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setScreenManager(ScreenManager screenManager) {
        super.setScreenManager(screenManager);
        this.connectionService.setScreenManager(getScreenManager());
    }



    public void setProgressNotification(Preloader.ProgressNotification progressNotification) {
        this.progressBar.setProgress(progressNotification.getProgress());
    }
}
