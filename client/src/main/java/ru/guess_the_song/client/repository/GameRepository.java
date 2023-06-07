package ru.guess_the_song.client.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.service.ConnectionService;
import ru.guess_the_song.core.dto.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class GameRepository {
    private final ConnectionService connectionService;
    private final PlayerRepository playerRepository;

    public GameRepository(ConnectionService connectionService, PlayerRepository playerRepository) {
        this.connectionService = connectionService;
        this.playerRepository = playerRepository;
    }

    public Optional<GameDto> create(UserDto initiator, MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto) {
        CreateGameResponseDto createGameResponseDto;

        try {
            this.connectionService.send(CreateGameDto.builder().initiatorId(initiator.getId()).musicPack(musicPackWithCorrectAnswersDto).build());
            createGameResponseDto = this.connectionService.waitObject(CreateGameResponseDto.class);
            log.debug(String.valueOf(createGameResponseDto));
        } catch (IOException e) {
            return Optional.empty();
        }
//        if (createGameResponseDto == null) return Optional.empty();
        if (createGameResponseDto.getStatus() == Status.ERROR) return Optional.empty();
        GameDto game = createGameResponseDto.getGame();

        Arrays.stream(game.getPlayers()).forEach(this.playerRepository::add);

        return Optional.of(game);
    }
}
