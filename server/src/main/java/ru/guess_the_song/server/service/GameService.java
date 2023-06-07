package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    Optional<Game> create(User initiator, MusicPack musicPack, Session session);

    Optional<Game> join(UUID gameId, User user, Session session);

    void start(UUID gameId, User user);

    void giveAnswer(UUID gameId, User user, int answerId);

//    void notifyPlayersNewPlayerJoined(UUID gameId, User user);
    List<Game> getAll();
    List<Game> getAllActive();
}
