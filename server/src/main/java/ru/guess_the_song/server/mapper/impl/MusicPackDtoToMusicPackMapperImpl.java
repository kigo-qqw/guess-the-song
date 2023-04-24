package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.MusicPackDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackDtoToMusicPackMapper;

public class MusicPackDtoToMusicPackMapperImpl implements MusicPackDtoToMusicPackMapper {
    @Override
    public MusicPack map(MusicPackDto data) {
        return MusicPack.builder().id(data.getUuid()).build();  // FIXME: 24.04.2023 
    }
}
