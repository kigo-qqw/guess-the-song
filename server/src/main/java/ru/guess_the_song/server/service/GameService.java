package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;

public interface GameService {
    Optional<Game> create(User initiator, MusicPack musicPack);
}
