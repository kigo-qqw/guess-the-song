package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface PlayerService {
    Optional<Player> create(Game game, User user);

    Optional<Player> get(Game game, User user);

    Optional<Player> get(UUID id);

    void increasePoints(Player player, int points);
}
