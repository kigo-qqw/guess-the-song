package ru.guess_the_song.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.client.repository.GameRepository;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.UserDto;

import java.util.Optional;

@Slf4j
@Component
public class GameService {
    private final GameRepository gameRepository;
    private GameDto game = null;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<GameDto> create(UserDto initiator, MusicPackWithCorrectAnswersDto musicPackWithCorrectAnswersDto) {
        Optional<GameDto> optionalGameDto = this.gameRepository.create(initiator, musicPackWithCorrectAnswersDto);
        optionalGameDto.ifPresent(gameDto -> this.game = gameDto);
        return optionalGameDto;
    }
}
