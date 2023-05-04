package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.StartGameDto;
import ru.guess_the_song.server.controller.StartGameController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class StartGameControllerImpl implements StartGameController {
    private final GameService gameService;
    private final UserService userService;

    public StartGameControllerImpl(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void request(Session session, StartGameDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isEmpty()) return;

        this.gameService.start(request.getGameId(), optionalUser.get());
    }
}
