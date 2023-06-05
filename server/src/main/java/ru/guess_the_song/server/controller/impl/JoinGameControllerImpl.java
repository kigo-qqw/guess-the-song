package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.JoinGameDto;
import ru.guess_the_song.server.controller.JoinGameController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class JoinGameControllerImpl implements JoinGameController {
    private final GameService gameService;
    private final UserService userService;

    public JoinGameControllerImpl(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void request(Session session, JoinGameDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isEmpty()) return;
        this.gameService.join(request.getGameId(), optionalUser.get(), session);
    }
}