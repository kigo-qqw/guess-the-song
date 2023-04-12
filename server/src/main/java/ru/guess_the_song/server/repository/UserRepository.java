package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    Optional<User> findById(UUID id);

    <S extends User> S save(S entity);
}
