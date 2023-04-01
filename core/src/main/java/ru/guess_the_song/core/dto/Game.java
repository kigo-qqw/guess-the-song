package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record Game(
        UUID id,
        List<Player> players
) implements Serializable {
}
