package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.GameRepository;
import ru.guess_the_song.core.dto.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class GameService {
    private final ConnectionService connectionService;
    private final GameRepository gameRepository;
    private GameDto game = null;

    public GameService(ConnectionService connectionService, GameRepository gameRepository) {
        this.connectionService = connectionService;
        this.gameRepository = gameRepository;
    }

    public Optional<GameDto> create(UserDto initiator, MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto) {
        Optional<GameDto> optionalGameDto = this.gameRepository.create(initiator, musicPackWithCorrectAnswersDto);
        optionalGameDto.ifPresent(gameDto -> this.game = gameDto);
        return optionalGameDto;
    }

    public void start(UserDto initiator) throws IOException {
        if (game == null) return;
        this.connectionService.send(StartGameDto.builder().gameId(game.getId()).userId(initiator.getId()).build());
    }

    public List<GameDto> getAll() throws IOException {
        this.connectionService.send(GetActiveGamesDto.builder().build());
        GetActiveGamesResponseDto getActiveGamesResponseDto = this.connectionService.waitObject(GetActiveGamesResponseDto.class);
        return Arrays.stream(getActiveGamesResponseDto.getGames()).toList();
    }
}
