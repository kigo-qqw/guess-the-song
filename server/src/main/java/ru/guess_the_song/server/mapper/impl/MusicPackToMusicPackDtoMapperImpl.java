package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.MusicPackDto;
import ru.guess_the_song.core.dto.SongEntryDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackDtoMapper;

@Slf4j
@Component
public class MusicPackToMusicPackDtoMapperImpl implements MusicPackToMusicPackDtoMapper {
    @Override
    public MusicPackDto map(MusicPack data) {
        return MusicPackDto.builder()
                .uuid(data.getId())
                .songs(data.getSongs().stream().map(songEntry -> SongEntryDto.builder().build()).toList())  // FIXME: 23.04.2023
                .build();
    }
}
