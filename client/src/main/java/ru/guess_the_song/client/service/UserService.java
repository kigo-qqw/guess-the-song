package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.UserRepository;
import ru.guess_the_song.core.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserService {
    private final UserRepository userRepository;
    private UserDto user = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> login(String username) {
        Optional<UserDto> optionalUserDto = this.userRepository.getUser(username);
        optionalUserDto.ifPresent(userDto -> this.user = userDto);
        return optionalUserDto;

    }

    public Optional<UserDto> get() {
        return Optional.of(this.user);
    }

    public Optional<UserDto> getById(UUID userId) {
        return this.userRepository.findById(userId);
    }
}
