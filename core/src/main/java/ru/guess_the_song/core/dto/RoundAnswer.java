package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.UUID;

public record RoundAnswer(UUID songId, String correctAnswer) implements Serializable {

}
