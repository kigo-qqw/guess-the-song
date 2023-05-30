module ru.guess_the_song.client {
    requires ru.guess_the_song.core;

    requires javafx.controls;
    requires javafx.fxml;

    // requires org.slf4j;
    // requires org.apache.logging.log4j;

    // requires spring.context;
    // requires spring.beans;
    // requires static lombok;

    opens ru.guess_the_song.client to javafx.fxml;

    exports ru.guess_the_song.client;
}
