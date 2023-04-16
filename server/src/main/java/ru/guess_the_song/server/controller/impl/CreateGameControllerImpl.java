package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.CreateGameDto;
import ru.guess_the_song.core.dto.CreateGameResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.service.GameService;

@Slf4j
@Component
public class CreateGameControllerImpl implements CreateGameController {
    private final GameService gameService;

    public CreateGameControllerImpl(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Result<CreateGameResponseDto> request(CreateGameDto request) {
        return null;
    }
}
