package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.core.dto.HealthCheckDto;
import ru.guess_the_song.core.dto.HealthCheckResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.service.HealthCheckService;

public class HealthCheckServiceImpl implements HealthCheckService {
    @Override
    public Result<HealthCheckResponseDto> check(HealthCheckDto request) {
        return Result.of(HealthCheckResponseDto.builder().build());
    }
}
