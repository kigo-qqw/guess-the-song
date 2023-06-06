package ru.guess_the_song.client.main;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.config.AppConfig;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.client.ui.ScreenManager;
import ru.guess_the_song.client.ui.controller.LoginController;
import ru.guess_the_song.client.ui.controller.SplashScreenController;

import java.io.IOException;

@Slf4j
@Component
public class GuessTheSongApplication extends Preloader {
    private ConfigurableApplicationContext applicationContext;
    private ScreenManager screenManager;

    @Value("${server.address:localhost}")
    private String address;
    @Value("${server.port:1234}")
    private int port;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(Application.class, () -> GuessTheSongApplication.this);
            applicationContext.registerBean(Parameters.class, this::getParameters);
            applicationContext.registerBean(HostServices.class, this::getHostServices);
            applicationContext.registerBean(AppConfig.class, AppConfig::new);
        };

        this.applicationContext = new SpringApplicationBuilder()
                .sources(Main.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));

        //        this.applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        log.debug("--address=" + this.address);
        log.debug("--port=" + this.port);
        primaryStage.show();

        this.screenManager = new ScreenManager(primaryStage, this.applicationContext);
        SplashScreenController splashScreenController = this.screenManager.changeWindow(SplashScreenController.class);

        Thread.sleep(2000);
        splashScreenController.setProgressNotification(new ProgressNotification(0.5));
        handleStateChangeNotification(new StateChangeNotification(StateChangeNotification.Type.BEFORE_LOAD));
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        this.screenManager.changeWindow(LoginController.class);
    }

    @Override
    public void stop(){
        Platform.exit();
        System.exit(0);
    }
}