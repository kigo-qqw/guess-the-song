package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackWithCorrectAnswersDtoMapper;

import java.util.List;

@Slf4j
@Component
public class MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl implements MusicPackToMusicPackWithCorrectAnswersDtoMapper {
    @Override
    public MusicPackWithCorrectAnswersDto map(MusicPack data) {
        List<SongEntry> songs = List.of(); // FIXME: 23.04.2023
        return MusicPackWithCorrectAnswersDto.builder()
                .uuid(data.getId())
                .songs(songs.stream().map(songEntry -> SongEntryWithCorrectAnswerDto.builder().build()).toList())
                .build();
    }
}
