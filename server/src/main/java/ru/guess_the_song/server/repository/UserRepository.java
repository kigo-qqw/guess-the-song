package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.User;

import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
}
