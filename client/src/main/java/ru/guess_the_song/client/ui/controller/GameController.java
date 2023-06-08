package ru.guess_the_song.client.ui.controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.StartRoundDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
public class GameController extends BaseController {
    public static final String FXML_PATH = "/ru/guess_the_song/client/ui/fxml/game-view.fxml";
    public static final String TITLE = "GAME";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startRound(StartRoundDto startRoundDto) {
//        Media song = new Media(startRoundDto.getSong().getData());
//        new MediaPlayer()
        log.debug("start round");
        try {
            File tempMp3 = File.createTempFile("VACA", ".mp3", null); //, getCacheDir()
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(startRoundDto.getSong().getData());
            fos.close();

//            System.out.println(tempMp3.getAbsolutePath());

            log.debug(tempMp3.toURI().toString());
//            final Media media = new Media(tempMp3.toURI().toURL().toString());
//            final Media media = new Media(tempMp3.toURI().toString());
//            log.debug("media=" + media);
//            final MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();

            AudioClip audioClip = new AudioClip(tempMp3.toURI().toString());
            audioClip.play();

//            AudioInputStream

        } catch (IOException e) {

        }
    }

    public void endRound() {

    }
}
