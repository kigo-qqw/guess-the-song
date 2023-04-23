package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends Repository<Game, UUID> {
    Optional<Game> findById(UUID id);

    <S extends Game> S save(S entity);
}
