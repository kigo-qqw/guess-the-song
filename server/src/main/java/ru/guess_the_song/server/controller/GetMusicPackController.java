package ru.guess_the_song.server.controller;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.service.MusicPackService;
import ru.guess_the_song.server.service.impl.MusicPackServiceImpl;

@Slf4j
public class GetMusicPackController implements Controller<GetMusicPackDto, GetMusicPackResponseDto> {
    private final MusicPackService musicPackService;

    public GetMusicPackController() {
        this.musicPackService = new MusicPackServiceImpl();
    }

    @Override
    public Result<GetMusicPackResponseDto> request(GetMusicPackDto request) {
        return this.musicPackService.get(request);
    }
}
