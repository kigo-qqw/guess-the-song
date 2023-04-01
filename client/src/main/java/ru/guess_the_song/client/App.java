package ru.guess_the_song.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        TextField textField = new TextField();
        Button sendButton = new Button("send");

        HBox hbox = new HBox(textField, sendButton);

        try {
            Socket socket = new Socket("localhost", 1234);
//            PrintWriter cout = new PrintWriter(socket.getOutputStream(), true);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            sendButton.setOnAction(event -> {
                try {
                    oos.writeObject(textField.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(new VBox(l, hbox), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
