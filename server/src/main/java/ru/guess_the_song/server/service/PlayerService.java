package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;

public interface PlayerService {
    Optional<Player> create(User user);
    Optional<Player> get(User user);
}
