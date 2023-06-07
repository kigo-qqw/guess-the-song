package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.GetActiveGamesDto;
import ru.guess_the_song.core.dto.GetActiveGamesResponseDto;
import ru.guess_the_song.core.dto.Status;
import ru.guess_the_song.server.controller.GetActiveGamesController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;

import java.util.List;

@Slf4j
@Component
public class GetActiveGamesControllerImpl implements GetActiveGamesController {
    private final GameService gameService;
    private final GameToGameDtoMapper gameToGameDtoMapper;

    public GetActiveGamesControllerImpl(GameService gameService, GameToGameDtoMapper gameToGameDtoMapper) {
        this.gameService = gameService;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
    }

    @Override
    public void request(Session session, GetActiveGamesDto request) {
        List<Game> activeGames = this.gameService.getAllActive();
        GameDto[] activeGameDtos = activeGames.stream().map(this.gameToGameDtoMapper::map).toArray(GameDto[]::new);
        session.send(GetActiveGamesResponseDto.builder()
                .status(Status.OK)
                .games(activeGameDtos)
                .build());
    }
}
