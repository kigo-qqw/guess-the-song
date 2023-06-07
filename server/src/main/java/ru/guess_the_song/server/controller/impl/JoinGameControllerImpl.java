package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.JoinGameDto;
import ru.guess_the_song.core.dto.JoinGameResponseDto;
import ru.guess_the_song.core.dto.Status;
import ru.guess_the_song.server.controller.JoinGameController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class JoinGameControllerImpl implements JoinGameController {
    private final GameService gameService;
    private final UserService userService;
    private final GameToGameDtoMapper gameToGameDtoMapper;

    public JoinGameControllerImpl(GameService gameService, UserService userService, GameToGameDtoMapper gameToGameDtoMapper) {
        this.gameService = gameService;
        this.userService = userService;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
    }

    @Override
    public void request(Session session, JoinGameDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isEmpty()) return;
        Optional<Game> optionalGame = this.gameService.join(request.getGameId(), optionalUser.get(), session);
        if (optionalGame.isPresent())
            session.send(JoinGameResponseDto.builder()
                    .status(Status.OK)
                    .game(this.gameToGameDtoMapper.map(optionalGame.get()))
                    .build()
            );
        else
            session.send(JoinGameResponseDto.builder().status(Status.ERROR).build());
    }
}