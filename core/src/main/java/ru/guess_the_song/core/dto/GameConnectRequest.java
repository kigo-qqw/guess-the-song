package ru.guess_the_song.core.dto;

import java.io.Serializable;

public record GameConnectRequest(
        Player player
) implements Serializable {
}
