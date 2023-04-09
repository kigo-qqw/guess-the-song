package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.service.MusicPackService;

import java.util.List;
import java.util.UUID;

public class MusicPackServiceImpl implements MusicPackService {

    @Override
    public Result<GetMusicPackResponseDto> get(GetMusicPackDto request) {
        // TODO

        return Result.of(
                GetMusicPackResponseDto.builder()
                        .uuid(request.getUuid())
                        .songs(
                                List.of(
                                        SongEntryDto.builder()
                                                .song(SongDto.builder()
                                                        .uuid(UUID.randomUUID())
                                                        .build()
                                                ).answers(List.of(
                                                        "[1] - [1]",
                                                        "[1] - [2]",
                                                        "[1] - [3]",
                                                        "[1] - [4]")
                                                ).build(),
                                        SongEntryDto.builder()
                                                .song(SongDto.builder()
                                                        .uuid(UUID.randomUUID())
                                                        .build()
                                                ).answers(List.of(
                                                        "[2] - [1]",
                                                        "[2] - [2]",
                                                        "[2] - [3]",
                                                        "[2] - [4]")
                                                ).build()
                                )
                        ).build()
        );

    }
}
