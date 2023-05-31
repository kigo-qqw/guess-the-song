package ru.guess_the_song.client.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.*;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class GameRepository {
    private final ConnectionService connectionService;

    public GameRepository(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public Optional<GameDto> create(UserDto initiator, MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto) {
        CreateGameResponseDto createGameResponseDto;

        try {
            this.connectionService.send(CreateGameDto.builder().initiatorId(initiator.getId()).musicPack(musicPackWithCorrectAnswersDto).build());
            createGameResponseDto = (CreateGameResponseDto) this.connectionService.waitObject();
            log.debug(String.valueOf(createGameResponseDto));
        } catch (IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
        if (createGameResponseDto == null) return Optional.empty();
        return Optional.of(createGameResponseDto.getGame());
    }
}
