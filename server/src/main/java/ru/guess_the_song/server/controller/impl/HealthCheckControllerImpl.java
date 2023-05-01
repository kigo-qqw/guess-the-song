package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.HealthCheckDto;
import ru.guess_the_song.server.controller.HealthCheckController;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.HealthCheckService;
import ru.guess_the_song.server.service.impl.HealthCheckServiceImpl;

//@Slf4j
//@Component
//public class HealthCheckControllerImpl implements HealthCheckController {
//    private final HealthCheckService healthCheckService;
//
//    public HealthCheckControllerImpl() {
//        this.healthCheckService = new HealthCheckServiceImpl();
//    }
//
//    @Override
//    public Result<HealthCheckResponseDto> request(Session session, HealthCheckDto request) {
//        return this.healthCheckService.check(request);
//    }
//}
@Slf4j
@Component
public class HealthCheckControllerImpl implements HealthCheckController {
    private final HealthCheckService healthCheckService;

    public HealthCheckControllerImpl() {
        this.healthCheckService = new HealthCheckServiceImpl();
    }

    @Override
    public void request(Session session, HealthCheckDto request) {
        session.send(this.healthCheckService.check(request));  // FIXME: 26.04.2023 
    }
}