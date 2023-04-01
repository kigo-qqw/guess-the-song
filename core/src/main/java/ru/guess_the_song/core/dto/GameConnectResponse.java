package ru.guess_the_song.core.dto;

import java.io.Serializable;

public record GameConnectResponse(
        Game game,
        GameConnectResponseStatus status
) implements Serializable {
    public enum GameConnectResponseStatus {
        CONNECTED, ERROR
    }
}
