package ru.guess_the_song.server.controller;

import lombok.extern.slf4j.Slf4j;

import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.service.HealthCheckService;
import ru.guess_the_song.server.service.impl.HealthCheckServiceImpl;

@Slf4j
public class HealthCheckController implements Controller<HealthCheckDto, HealthCheckResponseDto> {
    private final HealthCheckService healthCheckService;

    public HealthCheckController() {
        this.healthCheckService = new HealthCheckServiceImpl();
    }

    @Override
    public Result<HealthCheckResponseDto> request(HealthCheckDto request) {
        return this.healthCheckService.check(request);
    }
}
