package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackWithCorrectAnswersDtoToMusicPackMapper;
import ru.guess_the_song.server.mapper.SongEntryWithCorrectAnswerDtoToSongEntryMapper;

import java.util.Arrays;

@Slf4j
@Component
public class MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl implements MusicPackWithCorrectAnswersDtoToMusicPackMapper {
    private final SongEntryWithCorrectAnswerDtoToSongEntryMapper songEntryWithCorrectAnswerDtoToSongEntryMapper;

    public MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl(SongEntryWithCorrectAnswerDtoToSongEntryMapper songEntryWithCorrectAnswerDtoToSongEntryMapper) {
        this.songEntryWithCorrectAnswerDtoToSongEntryMapper = songEntryWithCorrectAnswerDtoToSongEntryMapper;
    }

    @Override
    public MusicPack map(MusicPackWithCorrectAnswersDto data) {
        return MusicPack.builder()
                .id(data.getId())
                .songs(Arrays.stream(data.getSongs())
                        .map(this.songEntryWithCorrectAnswerDtoToSongEntryMapper::map)
                        .toList())
                .build();  // FIXME: 24.04.2023

    }
}
