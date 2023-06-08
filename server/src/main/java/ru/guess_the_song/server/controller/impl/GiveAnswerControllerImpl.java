package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.GiveAnswerDto;
import ru.guess_the_song.server.controller.GiveAnswerController;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.PlayerService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

public class GiveAnswerControllerImpl implements GiveAnswerController {
    private final GameService gameService;
    private final UserService userService;
    private final PlayerService playerService;

    public GiveAnswerControllerImpl(GameService gameService, UserService userService, PlayerService playerService) {
        this.gameService = gameService;
        this.userService = userService;
        this.playerService = playerService;
    }

    @Override
    public void request(Session session, GiveAnswerDto request) {
//        Optional<User> optionalUser = this.userService.get(request.getUserId());
//        if(optionalUser.isEmpty()) return;
//         this.gameService.
        Optional<Player> optionalPlayer = this.playerService.get(request.getPlayerId());
        if (optionalPlayer.isEmpty()) return;
        this.gameService.giveAnswer(request.getGameId(), optionalPlayer.get(), request.getAnswerId());
    }
}
