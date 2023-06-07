package ru.guess_the_song.client.ui.controller;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.JoinGameResponseDto;
import ru.guess_the_song.core.dto.PlayerDto;
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
    private final PlayerRepository playerRepository;
    @FXML
    private TableView<GameItem> activeGamesTableView;
    @FXML
    private TableColumn<GameItem, UUID> activeGamesUuidTableColumn;
    @FXML
    private TableColumn<GameItem, String> activeGamesLeaderTableColumn;
    @FXML
    private TableColumn<GameItem, Number> activeGamesPlayerAmountTableColumn;
    @FXML
    private TableColumn<GameItem, Button> activeGamesConnectTableColumn;
    @FXML
    private Button createNewGameButton;

    public GameListController(GameService gameService, UserService userService, PlayerRepository playerRepository) {
        this.gameService = gameService;
        this.userService = userService;
        this.playerRepository = playerRepository;
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


//        this.activeGamesUuidTableColumn.setCellValueFactory(gameCellDataFeatures ->
//                new SimpleObjectProperty<>(gameCellDataFeatures.getValue().getId()));
//        this.activeGamesLeaderTableColumn.setCellValueFactory(gameCellDataFeatures -> {
//            String leaderName = "unknown";
//            Optional<PlayerDto> optionalPlayerDto = this.playerRepository.get(gameCellDataFeatures.getValue().getLeaderId());
//            if (optionalPlayerDto.isPresent()) {
//                Optional<UserDto> optionalUserDto = this.userService.getById(optionalPlayerDto.get().getUserId());
//                if (optionalUserDto.isPresent()) {
//                    leaderName = optionalUserDto.get().getUsername();
//                }
//            }
//            return new SimpleStringProperty(leaderName);
//        });
//        this.activeGamesPlayerAmountTableColumn.setCellValueFactory(gameCellDataFeatures -> {
//            return new SimpleIntegerProperty(gameCellDataFeatures.getValue().getPlayers().length);
//        });
//        this.activeGamesConnectTableColumn.setCellValueFactory(gameCellDataFeatures -> {
//            return new SimpleObjectProperty<>(new Button("connect"));
//        });

        this.activeGamesUuidTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().uuidProperty);
        this.activeGamesLeaderTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().leaderNameProperty);
        this.activeGamesPlayerAmountTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().playerAmountProperty);
        this.activeGamesConnectTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().connectButtonProperty);


        try {
            ObservableList<GameDto> gameDtoObservableList = FXCollections.observableList(this.gameService.getAll());
            ObservableList<GameItem> gameItems = FXCollections.observableArrayList(
                    gameDtoObservableList.stream().map(this::mapGameDtoToGameItem).toList()
            );

            gameDtoObservableList.addListener((ListChangeListener<GameDto>) change -> {
                Platform.runLater(() -> {
                    if (change.wasAdded())
                        gameItems.addAll(change.getAddedSubList().stream().map(this::mapGameDtoToGameItem).toList());
                    if (change.wasRemoved())
                        gameItems.removeAll(change.getRemoved().stream().map(this::mapGameDtoToGameItem).toList());
                });
            });

            this.activeGamesTableView.setItems(gameItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.createNewGameButton.setOnAction(event -> {
            CreateGameController createGameController = this.openDialogWindow(CreateGameController.class);
            Optional<GameDto> optionalGameDto = createGameController.result();
            if (optionalGameDto.isPresent())
                changeWindow(GameBeforeStartController.class);
        });
    }

    private GameItem mapGameDtoToGameItem(GameDto gameDto) {
        String leaderName = "unknown";

        Optional<PlayerDto> optionalPlayerDto = this.playerRepository.get(gameDto.getLeaderId());
        if (optionalPlayerDto.isPresent()) {
            Optional<UserDto> optionalUserDto = this.userService.getById(optionalPlayerDto.get().getUserId());
            if (optionalUserDto.isPresent()) {
                leaderName = optionalUserDto.get().getUsername();
            }
        }

        int playersAmount = gameDto.getPlayers().length;
        Button connectButton = new Button("connect");

        connectButton.setOnAction(event -> {
            Optional<UserDto> optionalUserDto = this.userService.get();
            if (optionalUserDto.isEmpty()) changeWindow(LoginController.class);
            else {
                try {
                    this.gameService.join(gameDto, optionalUserDto.get());
                    changeWindow(GameBeforeStartController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return new GameItem(
                gameDto.getId(), leaderName, playersAmount, connectButton
        );
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
