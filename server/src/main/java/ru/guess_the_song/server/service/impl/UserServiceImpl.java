package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.UserRepository;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> create(String username) {
        return Optional.of(this.userRepository.save(new User(username)));
    }

    @Override
    public Optional<User> get(UUID id) {
        return this.userRepository.findById(id);
    }
}
