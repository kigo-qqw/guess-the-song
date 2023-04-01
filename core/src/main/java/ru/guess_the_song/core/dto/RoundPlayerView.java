package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.List;

public record RoundPlayerView(
        Song song,
        List<String> answers
) implements Serializable {
}
