package ru.guess_the_song.client.ui.controller;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class SplashScreenController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/splash-screen-view.fxml";
    public static final String TITLE = "SPLASH SCREEN";

    @FXML
    private ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setProgressNotification(Preloader.ProgressNotification progressNotification) {
        this.progressBar.setProgress(progressNotification.getProgress());
    }
}
