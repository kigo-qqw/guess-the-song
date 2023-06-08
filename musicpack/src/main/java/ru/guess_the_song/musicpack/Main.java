package ru.guess_the_song.musicpack;


import ru.guess_the_song.core.dto.*;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Main {
    public static SongEntryWithCorrectAnswerDto loadSong(String name, String[] answers, int correctAnswerIdx) throws IOException {
        return SongEntryWithCorrectAnswerDto.builder()
                .data(Objects.requireNonNull(Main.class.getResourceAsStream("/ru/guess_the_song/musicpack/music/" + name)).readAllBytes())
                .answers(answers)
                .correctAnswerIdx(correctAnswerIdx)
                .build();
    }

    public static void main(String[] args) throws IOException {
        String[] answers = new String[]{
                "Disturbed - Open Your Eyes",
                "Disturbed - Down with the Sickness",
                "Disturbed - Immortalized",
                "Disturbed - Hell"
        };
        MusicPackWithCorrectAnswersDto musicPack = MusicPackWithCorrectAnswersDto.builder()
                .songs(new SongEntryWithCorrectAnswerDto[]{
                        loadSong("Disturbed - Open Your Eyes.mp3", answers, 0),
                        loadSong("Disturbed - Down with the Sickness.mp3", answers, 1),
                        loadSong("Disturbed - Immortalized.mp3", answers, 2),
                        loadSong("Disturbed - Hell.mp3", answers, 3)
                })
                .build();

        File file = new File("/home/kigo/disturbed.gtsmp");
        file.createNewFile();
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(musicPack);
        }
    }
}
