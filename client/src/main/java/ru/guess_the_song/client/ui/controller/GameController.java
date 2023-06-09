package ru.guess_the_song.client.ui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.PlayerRepository;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.client.ui.components.PlayerList;
import ru.guess_the_song.core.dto.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Slf4j
@Component
public class GameController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-view.fxml";
    public static final String TITLE = "GAME";
    private final UserService userService;
    private final GameService gameService;
    private final PlayerRepository playerRepository;
    private AudioClip audioClip;
    private SongEntryDto currentSong;
    private ToggleGroup toggleGroup;

    @FXML
    private PlayerList playerList;
    @FXML
    private VBox roundBox;
    @FXML
    private VBox answersBox;
    @FXML
    private Button giveAnswerButton;
    @FXML
    private VBox finishBox;
    @FXML
    private Label winnerLabel;

    public GameController(UserService userService, GameService gameService, PlayerRepository playerRepository) {
        this.userService = userService;
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.playerList.init(userService, true);
        this.playerList.setItems(this.playerRepository.getAll());

        this.giveAnswerButton.setOnAction(this::handleGiveAnswer);
    }

    public void startRound(StartRoundDto startRoundDto) {
        log.debug("start round");
        this.currentSong = startRoundDto.getSong();

        Platform.runLater(() -> {
            this.roundBox.setVisible(true);

            this.answersBox.getChildren().clear();
            this.toggleGroup = new ToggleGroup();

            for (String answer : this.currentSong.getAnswers()) {
                RadioButton radioButton = new RadioButton(answer);
                radioButton.setToggleGroup(this.toggleGroup);
                this.answersBox.getChildren().add(radioButton);
            }

            try {
                File tempMp3 = File.createTempFile("song", ".mp3", null);
                tempMp3.deleteOnExit();
                FileOutputStream fos = new FileOutputStream(tempMp3);
                fos.write(startRoundDto.getSong().getData());
                fos.close();

                this.audioClip = new AudioClip(tempMp3.toURI().toString());
                this.audioClip.play();

            } catch (IOException e) {
                // FIXME: 08.06.2023
            }
        });
    }

    public void endRound(EndRoundDto endRoundDto) {
        this.audioClip.stop();
    }

    private void handleGiveAnswer(ActionEvent event) {
        int index = Arrays.asList(this.currentSong.getAnswers()).indexOf(
                ((RadioButton) this.toggleGroup.getSelectedToggle()).getText()
        );

        log.debug("ANSWER INDEX={}", index);
        try {
            this.gameService.giveAnswer(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void finishGame(GameFinishedDto gameFinishedDto) {
        this.roundBox.setVisible(false);
        this.finishBox.setVisible(true);

        if (gameFinishedDto.getWinners().length == 1) {
            PlayerDto winner = gameFinishedDto.getWinners()[0];
            this.winnerLabel.setText(
                    winner.getUser().getUsername()
                            + " won with "
                            + winner.getPoints()
                            + " points"
            );
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("winners: ");
            for (int i = 0; i < gameFinishedDto.getWinners().length; i++) {
                PlayerDto winner = gameFinishedDto.getWinners()[i];
                stringBuilder.append(winner.getUser().getUsername());
                if (i < gameFinishedDto.getWinners().length - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(" with ")
                    .append(gameFinishedDto.getWinners()[0].getPoints())
                    .append(" points");
            this.winnerLabel.setText(stringBuilder.toString());
        }
    }
}
