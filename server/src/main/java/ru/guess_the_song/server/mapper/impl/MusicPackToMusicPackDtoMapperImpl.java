package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.MusicPackDto;
import ru.guess_the_song.core.dto.SongEntryDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackDtoMapper;

import java.util.List;

public class MusicPackToMusicPackDtoMapperImpl implements MusicPackToMusicPackDtoMapper {
    @Override
    public MusicPackDto map(MusicPack data) {
        List<SongEntry> songs = List.of(); // FIXME: 23.04.2023
        return MusicPackDto.builder()
                .uuid(data.getId())
                .songs(songs.stream().map(songEntry -> SongEntryDto.builder().build()).toList())  // FIXME: 23.04.2023
                .build();
    }
}
