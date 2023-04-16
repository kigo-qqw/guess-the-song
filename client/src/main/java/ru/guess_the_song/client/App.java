package ru.guess_the_song.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.guess_the_song.core.dto.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        UUID uuid = UUID.randomUUID();
        Label uuidLabel = new Label(uuid.toString());
        Label info = new Label();

        try {
            Socket socket = new Socket("localhost", 8000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


            oos.writeObject(CreateUserDto.builder().username("random username").build());
            Result<CreateUserResponseDto> response = (Result<CreateUserResponseDto>) ois.readObject();
            if (response != null)
                info.setText(response.get().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(new VBox(l, uuidLabel, info), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
