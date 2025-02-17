package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
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

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> create(String username) {
        return Optional.of(this.userRepository.save(User.builder().username(username).build()));
    }

    @Override
    public Optional<User> get(UUID id) {
        return this.userRepository.findById(id);
    }
}
