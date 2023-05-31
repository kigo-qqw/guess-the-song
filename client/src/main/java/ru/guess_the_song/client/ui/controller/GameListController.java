package ru.guess_the_song.client.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.guess_the_song.client.service.GameService;
import ru.guess_the_song.client.service.UserService;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.core.dto.UserDto;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameListController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-list-view.fxml";
    public static final String TITLE = "GAME LIST";
    private final UserService userService;
    private final GameService gameService;
    @FXML
    private Button createNewGameButton;

    public GameListController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public static SongEntryWithCorrectAnswerDto loadSong(String name, String[] answers, int correctAnswerIdx) throws IOException {
        return SongEntryWithCorrectAnswerDto.builder()
                .data(Objects.requireNonNull(GameListController.class.getResourceAsStream("/ru/guess_the_song/client/music/" + name)).readAllBytes())
                .answers(answers)
                .correctAnswerIdx(correctAnswerIdx)
                .build();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] answers = new String[]{
                "Disturbed - Open Your Eyes",
                "Disturbed - Down with the Sickness",
                "Disturbed - Immortalized",
                "Disturbed - Hell"
        };
        MusicPackWithCorrectAnswersDto musicPack;
        try {
            musicPack = MusicPackWithCorrectAnswersDto.builder()
                    .songs(new SongEntryWithCorrectAnswerDto[]{
                            loadSong("Disturbed - Open Your Eyes.mp3", answers, 0),
                            loadSong("Disturbed - Down with the Sickness.mp3", answers, 1),
                            loadSong("Disturbed - Immortalized.mp3", answers, 2),
                            loadSong("Disturbed - Hell.mp3", answers, 3)
                    })
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.createNewGameButton.setOnAction(event -> {
            Optional<UserDto> optionalUserDto = this.userService.get();
            if (optionalUserDto.isEmpty())
                this.changeWindow(LoginController.class);
            else
                this.gameService.create(
                        optionalUserDto.get(),
                        musicPack
                );
        });
    }
}
