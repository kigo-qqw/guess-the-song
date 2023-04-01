package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.UUID;

public record Player(
        UUID id,
        String name
) implements Serializable {
}
