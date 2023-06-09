package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;

import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    Optional<Game> create(User initiator, MusicPack musicPack, Session session);

    Optional<Game> join(UUID gameId, User user, Session session);

    void start(UUID gameId, User user);

    void giveAnswer(UUID gameId, Player player, int answerId);

    //    void notifyPlayersNewPlayerJoined(UUID gameId, User user);
    void notifySocketClose(Socket socket);

    List<Game> getAllActive();

    Optional<Game> get(UUID gameId);

    void leave(UUID gameId, User user);
}
