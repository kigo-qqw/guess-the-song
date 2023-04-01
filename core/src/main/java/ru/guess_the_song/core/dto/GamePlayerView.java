package ru.guess_the_song.core.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record GamePlayerView (
        UUID id,
        List<Player> players,
        List<RoundPlayerView> rounds
) implements Serializable {
}
