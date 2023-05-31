package ru.guess_the_song.client.main;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Application.launch(GuessTheSongApplication.class, args);
    }
}