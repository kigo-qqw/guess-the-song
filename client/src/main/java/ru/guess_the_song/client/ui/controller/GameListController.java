package ru.guess_the_song.client.ui.controller;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.UserDto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@Slf4j
@Component
public class GameListController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-list-view.fxml";
    public static final String TITLE = "GAME LIST";
    private final GameService gameService;
    private final UserService userService;
//    @FXML
//    private ListView<GameDto> activeGamesListView;

    @FXML
    private TableView<GameDto> activeGamesTableView;
    @FXML
    private TableColumn<GameDto, UUID> activeGamesUuidTableColumn;
    @FXML
    private TableColumn<GameDto, String> activeGamesLeaderTableColumn;
    @FXML
    private TableColumn<GameDto, Number> activeGamesPlayerAmountTableColumn;
    @FXML
    private TableColumn<GameDto, Button> activeGamesConnectTableColumn;
    @FXML
    private Button createNewGameButton;

    public GameListController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        this.songIndexTableColumn.setCellValueFactory(
//                songCellDataFeatures -> songCellDataFeatures.getValue().indexProperty.asObject());
//        this.songFilenameTableColumn.setCellValueFactory(
//                songCellDataFeatures -> songCellDataFeatures.getValue().filenameProperty);
//        this.songAnswersTableColumn.setCellValueFactory(
//                songCellDataFeatures -> {
//                    ListView<String> listView = new ListView<>(songCellDataFeatures.getValue().answersProperty);
//
//                    listView.setPrefHeight(songCellDataFeatures.getValue().answersProperty.size() * ROW_HEIGHT + 2);
//                    listView.setEditable(true);
//                    listView.setCellFactory(TextFieldListCell.forListView());
//
//                    return new SimpleObjectProperty<>(listView);
//                });
//
//        this.correctAnswerTableColumn.setCellValueFactory(songCellDataFeatures -> {
//            ChoiceBox<String> correctAnswerChoiceBox = new ChoiceBox<>(songCellDataFeatures.getValue().answersProperty);
//            correctAnswerChoiceBox.setValue(
//                    songCellDataFeatures.getValue().answersProperty.get(
//                            songCellDataFeatures.getValue().correctAnswerIndexProperty.get()));
//            correctAnswerChoiceBox.getSelectionModel()
//                    .selectedIndexProperty()
//                    .addListener((observable, oldValue, newValue) ->
//                            songCellDataFeatures.getValue().correctAnswerIndexProperty.set(newValue.intValue()));
//            return new SimpleObjectProperty<>(correctAnswerChoiceBox);
//        });
//
//        this.deleteSongButtonTableColumn.setCellValueFactory(
//                songCellDataFeatures -> songCellDataFeatures.getValue().deleteButtonProperty);
//
//        this.songsTableView.setItems(FXCollections.observableArrayList(List.of()));

        this.activeGamesUuidTableColumn.setCellValueFactory(gameCellDataFeatures ->
                new SimpleObjectProperty<>(gameCellDataFeatures.getValue().getId()));
        this.activeGamesLeaderTableColumn.setCellValueFactory(gameCellDataFeatures -> {
            Optional<UserDto> optionalUserDto = this.userService.getById(gameCellDataFeatures.getValue().getLeaderId());
            String leaderName = optionalUserDto.map(UserDto::getUsername).orElse("unknown");
            return new SimpleStringProperty(leaderName);
        });
        this.activeGamesPlayerAmountTableColumn.setCellValueFactory(gameCellDataFeatures -> {
            return new SimpleIntegerProperty(gameCellDataFeatures.getValue().getPlayers().length);
        });
        this.activeGamesConnectTableColumn.setCellValueFactory(gameCellDataFeatures -> {
            return new SimpleObjectProperty<>(new Button("connect"));
        });


        try {
            log.debug("getting active games");
            ObservableList<GameDto> gameDtoObservableList = FXCollections.observableList(this.gameService.getAll());
            this.activeGamesTableView.setItems(gameDtoObservableList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        this.activeGamesListView.setCellFactory(param -> new ListCell<>() {
//            @Override
//            public void updateItem(GameDto gameDto, boolean empty) {
//                super.updateItem(gameDto, empty);
//                if (empty || gameDto == null) {
//                    setText(null);
//                } else {
//                    setText(gameDto.getId().toString());
//                }
//            }
//        });
//
//        try {
//            log.debug("getting active games");
//            ObservableList<GameDto> gameDtoObservableList = FXCollections.observableList(this.gameService.getAll());
//            this.activeGamesListView.setItems(gameDtoObservableList);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        this.createNewGameButton.setOnAction(event -> {
            CreateGameController createGameController = this.openDialogWindow(CreateGameController.class);
            Optional<GameDto> optionalGameDto = createGameController.result();
            if (optionalGameDto.isPresent())
                this.changeWindow(GameBeforeStartController.class);
        });
    }

    @Getter
    @Setter
    @ToString
    private class GameItem {
        private final SimpleObjectProperty<UUID> uuidProperty;
        private final StringProperty leaderNameProperty;
        private final IntegerProperty playerAmountProperty;
        private final ObjectProperty<Button> connectButtonProperty;

        private GameItem(UUID uuid, String leaderName, int playerAmount, Button connectButton) {
            this.uuidProperty = new SimpleObjectProperty<>(uuid);
            this.leaderNameProperty = new SimpleStringProperty(leaderName);
            this.playerAmountProperty = new SimpleIntegerProperty(playerAmount);
            this.connectButtonProperty = new SimpleObjectProperty<>(connectButton);
        }
    }
}
