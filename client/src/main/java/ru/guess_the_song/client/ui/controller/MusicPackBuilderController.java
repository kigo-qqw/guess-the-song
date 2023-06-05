package ru.guess_the_song.client.ui.controller;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Component
public class MusicPackBuilderController extends BaseDialogController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/music-pack-builder-view.fxml";
    public static final String TITLE = "MUSIC PACK BUILDER";
    private static final int ROW_HEIGHT = 24;
    @FXML
    private TableView<SongItem> songsTableView;
    @FXML
    private TableColumn<SongItem, Integer> songIndexTableColumn;
    @FXML
    private TableColumn<SongItem, String> songFilenameTableColumn;
    @FXML
    private TableColumn<SongItem, ListView<String>> songAnswersTableColumn;
    @FXML
    private TableColumn<SongItem, ChoiceBox<String>> correctAnswerTableColumn;
    @FXML
    private TableColumn<SongItem, Button> deleteSongButtonTableColumn;
    @FXML
    private Button addSongButton;
    @FXML
    private Button saveMusicPackButton;

    private int lastIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.songIndexTableColumn.setCellValueFactory(
                songCellDataFeatures -> songCellDataFeatures.getValue().indexProperty.asObject());
        this.songFilenameTableColumn.setCellValueFactory(
                songCellDataFeatures -> songCellDataFeatures.getValue().filenameProperty);
        this.songAnswersTableColumn.setCellValueFactory(
                songCellDataFeatures -> {
                    ListView<String> listView = new ListView<>(songCellDataFeatures.getValue().answersProperty);

                    listView.setPrefHeight(songCellDataFeatures.getValue().answersProperty.size() * ROW_HEIGHT + 2);
                    listView.setEditable(true);
                    listView.setCellFactory(TextFieldListCell.forListView());

                    return new SimpleObjectProperty<>(listView);
                });

        this.correctAnswerTableColumn.setCellValueFactory(songCellDataFeatures -> {
            ChoiceBox<String> correctAnswerChoiceBox = new ChoiceBox<>(songCellDataFeatures.getValue().answersProperty);
            correctAnswerChoiceBox.setValue(
                    songCellDataFeatures.getValue().answersProperty.get(
                            songCellDataFeatures.getValue().correctAnswerIndexProperty.get()));
            correctAnswerChoiceBox.getSelectionModel()
                    .selectedIndexProperty()
                    .addListener((observable, oldValue, newValue) ->
                            songCellDataFeatures.getValue().correctAnswerIndexProperty.set(newValue.intValue()));
            return new SimpleObjectProperty<>(correctAnswerChoiceBox);
        });

        this.deleteSongButtonTableColumn.setCellValueFactory(
                songCellDataFeatures -> songCellDataFeatures.getValue().deleteButtonProperty);

        this.songsTableView.setItems(FXCollections.observableArrayList(List.of()));

        this.addSongButton.setOnAction(event -> addNewSongItem());

        this.saveMusicPackButton.setOnAction(this::handleSaveMusicPack);
    }

    private void addNewSongItem() {
        FileChooser fileOpenChooser = new FileChooser();
        fileOpenChooser.setTitle("Open");
        fileOpenChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Song", "*.mp3"));

        File file = fileOpenChooser.showOpenDialog(getStage());
        if (file == null) return;

        int songIndex = ++this.lastIndex;

        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(event -> {
            this.songsTableView.getItems().removeIf(songItem -> songItem.indexProperty.get() == songIndex);
            int index = 1;
            for (SongItem songItem : this.songsTableView.getItems()) {
                songItem.indexProperty.set(index++);
            }
        });

        SongItem songItem = new SongItem(
                songIndex,
                file.getName(),
                file.getAbsolutePath(),
                FXCollections.observableArrayList(List.of("1", "2", "3", "4")),
                0,
                deleteButton
        );

        this.songsTableView.getItems().

                add(songItem);
    }

    private void handleSaveMusicPack(ActionEvent event) {
        boolean allGood = true;
        ArrayList<SongEntryWithCorrectAnswerDto> songs = new ArrayList<>();
        for (SongItem songItem : this.songsTableView.getItems()) {
            File songFile = new File(songItem.absoluteFilePath);
            if (songFile.exists()) {
                try (FileInputStream songFileInputStream = new FileInputStream(songFile)) {
                    int correctAnswerIndex = songItem.correctAnswerIndexProperty.get();
                    if (correctAnswerIndex == -1) allGood = false;
                    songs.add(
                            SongEntryWithCorrectAnswerDto.builder()
                                    .data(songFileInputStream.readAllBytes())
                                    .answers(songItem.answersProperty.toArray(String[]::new))
                                    .correctAnswerIdx(songItem.correctAnswerIndexProperty.get())
                                    .build()
                    );
                } catch (IOException e) {
                    allGood = false;
                    break;
                }
            } else {
                allGood = false;
                break;
            }
        }
        if (allGood) {
            FileChooser fileSaveChooser = new FileChooser();
            fileSaveChooser.setTitle("Open");
            fileSaveChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Music Pack", "*.gtsmp"));

            File file = fileSaveChooser.showSaveDialog(getStage());
            if (file == null) {
                getStage().close();
            } else {
                try (FileOutputStream fos = new FileOutputStream(file);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(MusicPackWithCorrectAnswersDto.builder()
                            .songs(songs.toArray(SongEntryWithCorrectAnswerDto[]::new))
                            .build());
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                }
            }
        }
    }

    @ToString
    @Getter
    @Setter
    private class SongItem {
        private IntegerProperty indexProperty;
        private StringProperty filenameProperty;
        private String absoluteFilePath;
        private ListProperty<String> answersProperty;
        private IntegerProperty correctAnswerIndexProperty;
        private ObjectProperty<Button> deleteButtonProperty;

        public SongItem(
                int index,
                String filename,
                String absoluteFilePath,
                ObservableList<String> answers,
                int correctAnswerIndex,
                Button deleteButton
        ) {
            this.indexProperty = new SimpleIntegerProperty(index);
            this.filenameProperty = new SimpleStringProperty(filename);
            this.absoluteFilePath = absoluteFilePath;
            this.answersProperty = new SimpleListProperty<>(answers);
            this.correctAnswerIndexProperty = new SimpleIntegerProperty(correctAnswerIndex);
            this.deleteButtonProperty = new SimpleObjectProperty<>(deleteButton);
        }
    }
}
