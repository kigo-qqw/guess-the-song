package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;

import java.net.URL;
import java.util.ResourceBundle;

public class MusicPackBuilderController extends BaseDialogController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/music-pack-builder-view.fxml";
    public static final String TITLE = "MUSIC PACK BUILDER";
    @FXML
    private TableView<SongItem> songsTableView;
    @FXML private TableColumn<> songIndexTableColumn;
    @FXML
    private TableColumn<SongItem, String> songNameTableColumn;
    @FXML
    private TableColumn songFilenameTableColumn;
    @FXML
    private TableColumn deleteSongButtonTableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private class SongItem {

    }
}
