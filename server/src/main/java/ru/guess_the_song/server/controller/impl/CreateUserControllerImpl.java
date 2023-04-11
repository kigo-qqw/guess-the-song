package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.core.dto.CreateUserResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.controller.CreateUserController;

@Slf4j
public class CreateUserControllerImpl implements CreateUserController {
    @Override
    public Result<CreateUserResponseDto> request(CreateUserDto request) {
        return null;
    }
}
