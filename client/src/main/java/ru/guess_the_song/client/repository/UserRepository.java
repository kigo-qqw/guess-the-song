package ru.guess_the_song.client.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.core.dto.CreateUserResponseDto;
import ru.guess_the_song.core.dto.UserDto;

import java.io.IOException;
import java.util.EventListener;
import java.util.Optional;

@Slf4j
@Component
public class UserRepository {
    private final ConnectionService connectionService;

    public UserRepository(ConnectionService connectionService) {
        this.connectionService = connectionService;
        try {
            connectionService.connect("localhost", 8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserDto> getUser(String username) {
        CreateUserResponseDto createUserResponseDto;
        try {
            this.connectionService.send(CreateUserDto.builder().username(username).build());
            createUserResponseDto = (CreateUserResponseDto) this.connectionService.waitObject();

            log.debug(String.valueOf(createUserResponseDto));
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
        if (createUserResponseDto == null) return Optional.empty();
        return Optional.of(createUserResponseDto.getUser());
    }
}
