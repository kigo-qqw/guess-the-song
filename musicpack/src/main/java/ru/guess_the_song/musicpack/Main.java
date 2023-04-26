package ru.guess_the_song.musicpack;


import ru.guess_the_song.core.dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        try (Socket socket = new Socket("localhost", 8000)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(CreateUserDto.builder().username("kigo").build());
            Result<CreateUserResponseDto> createUserResponse = (Result<CreateUserResponseDto>) ois.readObject();

            CreateGameDto createGameDto = CreateGameDto.builder()
                    .initiator(createUserResponse.get().getUser())
                    .musicPack(musicPack)
                    .build();

            System.out.println(createGameDto);

            oos.writeObject(createGameDto);
            Result<CreateGameResponseDto> createGameResponse = (Result<CreateGameResponseDto>) ois.readObject();
            if (createGameResponse != null && createGameResponse.isPresent()) {
                System.out.println(createGameResponse.get().toString());

                GameDto gameDto = createGameResponse.get().getGame();
                for (SongEntryDto songEntryDto : gameDto.getMusicPack().getSongs()) {
                    System.out.println(songEntryDto.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
