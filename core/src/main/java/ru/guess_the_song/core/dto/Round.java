package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.List;

public record Round(
        Song song,
        List<String> answers,
        String correctAnswer
) implements Serializable {
}
