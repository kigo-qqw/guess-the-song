package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.GiveAnswerDto;
import ru.guess_the_song.server.controller.GiveAnswerController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.PlayerService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
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
        log.info("1");
        Optional<Game> optionalGame = this.gameService.get(request.getGameId());
        if (optionalGame.isEmpty()) return;
        log.info("2");
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isEmpty()) return;
        log.info("3");
        Optional<Player> optionalPlayer = this.playerService.get(optionalGame.get(), optionalUser.get());
        if (optionalPlayer.isEmpty()) return;
        log.info("4");
        this.gameService.giveAnswer(optionalGame.get().getId(), optionalPlayer.get(), request.getAnswerId());
    }
}
