package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.CreateGameDto;
import ru.guess_the_song.core.dto.CreateGameResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.controller.CreateGameController;

public class CreateGameControllerImpl implements CreateGameController {
    @Override
    public Result<CreateGameResponseDto> request(CreateGameDto request) {
        return null;
    }
}
