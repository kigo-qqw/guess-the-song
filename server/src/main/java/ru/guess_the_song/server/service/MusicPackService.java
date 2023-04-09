package ru.guess_the_song.server.service;

import ru.guess_the_song.core.dto.GetMusicPackDto;
import ru.guess_the_song.core.dto.GetMusicPackResponseDto;
import ru.guess_the_song.core.dto.Result;

public interface MusicPackService {
    Result<GetMusicPackResponseDto> get(GetMusicPackDto request);
}
