package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(String username);
}
