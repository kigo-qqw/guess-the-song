package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.entity.MusicPack;

import java.util.Optional;
import java.util.UUID;

public interface GameService {
    Optional<Game> create(User initiator, MusicPack musicPack);

    void start(UUID gameId, User user);
}
