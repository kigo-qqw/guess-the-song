package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MusicPackMenuController extends BaseDialogController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/music-pack-menu-view.fxml";
    public static final String TITLE = "MUSIC PACK MENU";
    private MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto = null;
    @FXML
    private Button loadMusicPackButton;
    @FXML
    private Button createMusicPackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileOpenChooser = new FileChooser();
        fileOpenChooser.setTitle("Open");
        fileOpenChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Music Pack", "*.gtsmp"));

        this.loadMusicPackButton.setOnAction(event -> {
            File file = fileOpenChooser.showOpenDialog(getStage());
            try {
                this.musicPackWithCorrectAnswersDto =
                        (MusicPackWithCorrectAnswersDto) new ObjectInputStream(new FileInputStream(file)).readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        this.createMusicPackButton.setOnAction(event -> {
            MusicPackBuilderController musicPackBuilderController = this.openDialogWindow(MusicPackBuilderController.class);

        });
    }

    public Optional<MusicPackWithCorrectAnswersDto> result() {
        return Optional.ofNullable(this.musicPackWithCorrectAnswersDto);
    }
}
