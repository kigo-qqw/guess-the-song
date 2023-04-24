package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackWithCorrectAnswersDtoToMusicPackMapper;

public class MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl implements MusicPackWithCorrectAnswersDtoToMusicPackMapper {
    @Override
    public MusicPack map(MusicPackWithCorrectAnswersDto data) {

        return MusicPack.builder().id(data.getUuid()).build();  // FIXME: 24.04.2023
    }
}
