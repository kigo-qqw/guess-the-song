package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.GetPlayerDto;
import ru.guess_the_song.core.dto.GetPlayerResponseDto;
import ru.guess_the_song.core.dto.Status;
import ru.guess_the_song.server.controller.GetPlayerController;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.PlayerService;

import java.util.Optional;

@Slf4j
@Component
public class GetPlayerControllerImpl implements GetPlayerController {
    private final PlayerService playerService;
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;

    public GetPlayerControllerImpl(PlayerService playerService, PlayerToPlayerDtoMapper playerToPlayerDtoMapper) {
        this.playerService = playerService;
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
    }

    @Override
    public void request(Session session, GetPlayerDto request) {
        Optional<Player> optionalPlayer = this.playerService.get(request.getId());
        log.debug("get player with uuid=" + request.getId());
        if (optionalPlayer.isPresent())
            session.send(GetPlayerResponseDto.builder()
                    .status(Status.OK)
                    .player(this.playerToPlayerDtoMapper.map(optionalPlayer.get()))
                    .build());
        else
            session.send(GetPlayerResponseDto.builder()
                    .status(Status.ERROR)
                    .build());
    }
}
