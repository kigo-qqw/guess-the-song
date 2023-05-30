package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.GiveAnswerDto;
import ru.guess_the_song.server.controller.GiveAnswerController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

public class GiveAnswerControllerImpl implements GiveAnswerController {
    private final GameService gameService;
    private final UserService userService;

    public GiveAnswerControllerImpl(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void request(Session session, GiveAnswerDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if(optionalUser.isEmpty()) return;
        // this.gameService.
    }
}
