package ru.guess_the_song.client.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserRepository {
    private final ConnectionService connectionService;

    public UserRepository(ConnectionService connectionService) {
        log.debug("UserRepository created");
        this.connectionService = connectionService;
//        try {
//            connectionService.connect("localhost", 8000);
//            log.debug("CS="+connectionService.socket);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public Optional<UserDto> getUser(String username) {
        CreateUserResponseDto createUserResponseDto;
        try {
            this.connectionService.send(CreateUserDto.builder().username(username).build());
            createUserResponseDto = this.connectionService.waitObject(CreateUserResponseDto.class);

            log.debug(String.valueOf(createUserResponseDto));
        } catch (IOException e) {
            return Optional.empty();
        }
        if (createUserResponseDto == null) return Optional.empty();
        return Optional.of(createUserResponseDto.getUser());
    }

    public Optional<UserDto> findById(UUID userId) {
        GetUserResponseDto getUserResponseDto;
        try {
            this.connectionService.send(GetUserDto.builder().userId(userId).build());
            getUserResponseDto = this.connectionService.waitObject(GetUserResponseDto.class);

            log.debug(String.valueOf(getUserResponseDto));
        } catch (IOException e) {
            return Optional.empty();
        }
        if (getUserResponseDto == null) return Optional.empty();
        return Optional.of(getUserResponseDto.getUser());
    }
}
