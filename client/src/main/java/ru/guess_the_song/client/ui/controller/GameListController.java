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
    @FXML
    private Button updateButton;

    public GameListController(GameService gameService, UserService userService, PlayerRepository playerRepository) {
        this.gameService = gameService;
        this.userService = userService;
        this.playerRepository = playerRepository;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.activeGamesUuidTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().uuidProperty);
        this.activeGamesLeaderTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().leaderNameProperty);
        this.activeGamesPlayerAmountTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().playerAmountProperty);
        this.activeGamesConnectTableColumn.setCellValueFactory(gameCellDataFeatures ->
                gameCellDataFeatures.getValue().connectButtonProperty);

        updateGames();

        this.createNewGameButton.setOnAction(event -> {
            CreateGameController createGameController = this.openDialogWindow(CreateGameController.class);
            Optional<GameDto> optionalGameDto = createGameController.result();
            if (optionalGameDto.isPresent())
                changeWindow(GameBeforeStartController.class);
        });

        this.updateButton.setOnAction(event -> updateGames());
    }

    private void updateGames() {
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
    }

    private GameItem mapGameDtoToGameItem(GameDto gameDto) {
        String leaderName = "unknown";

        Optional<PlayerDto> optionalPlayerDto = this.playerRepository.get(gameDto.getLeaderId());
        if (optionalPlayerDto.isPresent()) {
            leaderName = optionalPlayerDto.get().getUser().getUsername();
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
