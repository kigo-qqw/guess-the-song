module ru.guess_the_song.client {
    requires ru.guess_the_song.core;

    requires javafx.controls;
    requires javafx.fxml;

     requires org.slf4j;
     requires org.apache.logging.log4j;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires static lombok;

    opens ru.guess_the_song.client.main to spring.core;
    opens ru.guess_the_song.client.config to spring.core;
    opens ru.guess_the_song.client.ui.controller to javafx.fxml;
    opens ru.guess_the_song.client.ui.components to javafx.fxml;

    exports ru.guess_the_song.client.main;
    exports ru.guess_the_song.client.config;
}
