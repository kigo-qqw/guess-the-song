package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.GameRepository;
import ru.guess_the_song.client.repository.PlayerRepository;
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
    private final UserService userService;
    private final PlayerRepository playerRepository;
    private GameDto game = null;

    public GameService(ConnectionService connectionService, GameRepository gameRepository, UserService userService, PlayerRepository playerRepository) {
        this.connectionService = connectionService;
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerRepository = playerRepository;
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

    public Optional<GameDto> join(GameDto game, UserDto user) throws IOException {
        this.connectionService.send(JoinGameDto.builder().gameId(game.getId()).userId(user.getId()).build());
        JoinGameResponseDto joinGameResponseDto = this.connectionService.waitObject(JoinGameResponseDto.class);
        if (joinGameResponseDto.getStatus() == Status.ERROR) return Optional.empty();
        this.game = joinGameResponseDto.getGame();
        Arrays.stream(this.game.getPlayers()).forEach(this.playerRepository::add);
        return Optional.of(joinGameResponseDto.getGame());
    }

    public void giveAnswer(int answerIndex) throws IOException {
        Optional<UserDto> optionalUserDto = this.userService.get();
        if (optionalUserDto.isPresent()) {
            this.connectionService.send(GiveAnswerDto.builder()
                    .gameId(this.game.getId())
                    .userId(optionalUserDto.get().getId())
                    .answerId(answerIndex)
                    .build()
            );
        }
    }

    public List<GameDto> getAll() throws IOException {
        this.connectionService.send(GetActiveGamesDto.builder().build());
        GetActiveGamesResponseDto getActiveGamesResponseDto = this.connectionService.waitObject(GetActiveGamesResponseDto.class);
        if (getActiveGamesResponseDto.getStatus() == Status.ERROR) return List.of();
        return Arrays.stream(getActiveGamesResponseDto.getGames()).toList();
    }
}
