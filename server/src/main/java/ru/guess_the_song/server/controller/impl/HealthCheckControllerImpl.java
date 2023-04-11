package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.HealthCheckDto;
import ru.guess_the_song.core.dto.HealthCheckResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.controller.HealthCheckController;
import ru.guess_the_song.server.service.HealthCheckService;
import ru.guess_the_song.server.service.impl.HealthCheckServiceImpl;

@Slf4j
public class HealthCheckControllerImpl implements HealthCheckController {
    private final HealthCheckService healthCheckService;

    public HealthCheckControllerImpl() {
        this.healthCheckService = new HealthCheckServiceImpl();
    }

    @Override
    public Result<HealthCheckResponseDto> request(HealthCheckDto request) {
        return this.healthCheckService.check(request);
    }
}