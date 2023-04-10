package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.GetMusicPackResponseDto;
import ru.guess_the_song.core.dto.SongDto;
import ru.guess_the_song.core.dto.SongEntryDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackMapper;

public class MusicPackMapperImpl implements MusicPackMapper {
    @Override
    public GetMusicPackResponseDto map(MusicPack data) {
        return GetMusicPackResponseDto.builder()
                .uuid(data.getUuid())
                .songs(
                        data.getSongs().stream().map(
                                songEntry ->
                                        SongEntryDto.builder()
                                                .song(SongDto.builder()
                                                        .uuid(songEntry.getSong().getUuid())
                                                        .build())
                                                .answers(songEntry.getAnswers())
                                                .build()
                        ).toList()
                )
                .build();
    }
}
