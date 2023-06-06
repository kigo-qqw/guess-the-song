package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends Repository<Game, UUID> {
    Optional<Game> findById(UUID id);

    List<Game> findAll();

    <S extends Game> S save(S entity);
}
