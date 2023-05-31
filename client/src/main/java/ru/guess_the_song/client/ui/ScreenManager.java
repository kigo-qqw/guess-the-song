package ru.guess_the_song.client.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.ui.controller.BaseController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@Component
public class ScreenManager {
    private final Stage stage;
    private final ConfigurableApplicationContext applicationContext;

    public ScreenManager(Stage stage, ConfigurableApplicationContext applicationContext) {
        this.stage = stage;
        this.applicationContext = applicationContext;
    }

    public <T extends BaseController> T changeWindow(Class<T> controllerClass) {
        try {
//            this.stage.close();
            System.out.println(controllerClass.getField("FXML_PATH").get(null));

            FXMLLoader fxmlLoader = new FXMLLoader(controllerClass.getResource(
                    (String) controllerClass.getField("FXML_PATH").get(null)));

            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(fxmlLoader.load());

            T controller = fxmlLoader.getController();
            controller.setStage(this.stage);
            controller.setScreenManager(this);

            this.stage.setScene(scene);
            this.stage.setTitle(String.valueOf(controllerClass.getField("TITLE").get(null)));
//            this.stage.show();

            return controller;
        } catch (NoSuchFieldException | IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
