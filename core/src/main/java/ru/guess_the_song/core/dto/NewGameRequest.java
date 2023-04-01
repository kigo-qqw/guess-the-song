package ru.guess_the_song.core.dto;

import java.io.Serializable;

public record NewGameRequest(
        Player initiator
) implements Serializable {
}
