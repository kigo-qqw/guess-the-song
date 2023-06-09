package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends Repository<Player, UUID> {
    <S extends Player> S save(S entity);

    Optional<Player> findByGameAndUser(Game game, User user);

    Optional<Player> findById(UUID id);
}
