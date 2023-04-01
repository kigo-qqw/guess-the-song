package ru.guess_the_song.core.dto;

import java.io.Serializable;

public record NewGameResponse(
        Game game, NewGameResponseStatus status
) implements Serializable {
    public enum NewGameResponseStatus {
        CREATED, ERROR
    }
}
