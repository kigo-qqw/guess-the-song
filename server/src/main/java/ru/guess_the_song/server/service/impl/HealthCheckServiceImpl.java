package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.HealthCheckDto;
import ru.guess_the_song.core.dto.HealthCheckResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.service.HealthCheckService;

@Slf4j
@Component
public class HealthCheckServiceImpl implements HealthCheckService {
    @Override
    public Result<HealthCheckResponseDto> check(HealthCheckDto request) {
        return Result.of(HealthCheckResponseDto.builder().build());
    }
}
