package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.GetPlayerDto;
import ru.guess_the_song.server.controller.GetPlayerController;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.PlayerService;

public class GetPlayerControllerImpl implements GetPlayerController {
    private final PlayerService playerService;

    public GetPlayerControllerImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void request(Session session, GetPlayerDto request) {

    }
}
