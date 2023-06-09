package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.LeaveGameDto;
import ru.guess_the_song.server.controller.LeaveGameController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

public class LeaveGameControllerImpl implements LeaveGameController {
    private final GameService gameService;
    private final UserService userService;

    public LeaveGameControllerImpl(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void request(Session session, LeaveGameDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isEmpty()) return;

        this.gameService.leave(request.getGameId(), optionalUser.get());
    }
}
