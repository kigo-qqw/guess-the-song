package ru.guess_the_song.client.ui.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.client.ui.ScreenManager;

@Slf4j
public abstract class BaseController implements Initializable {
    public static final String FXML_PATH = null;
    public static final String TITLE = null;
    private Stage stage;
    private ScreenManager screenManager;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager() {
        return this.screenManager;
    }

    public <T extends BaseController> T changeWindow(Class<T> controllerClass) {
        log.debug("change window to=" + controllerClass);
        return this.screenManager.changeWindow(controllerClass);
    }

    public <T extends BaseDialogController> T openDialogWindow(Class<T> dialogControllerClass) {
        return this.screenManager.openDialogWindow(dialogControllerClass);
    }

    public Stage getStage() {
        return this.stage;
    }

    public void close() {
        this.stage.close();
    }
}
