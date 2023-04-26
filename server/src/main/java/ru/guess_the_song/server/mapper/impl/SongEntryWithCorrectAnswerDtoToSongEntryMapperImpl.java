package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.SongEntryWithCorrectAnswerDtoToSongEntryMapper;

import java.util.Arrays;

@Slf4j
@Component
public class SongEntryWithCorrectAnswerDtoToSongEntryMapperImpl implements SongEntryWithCorrectAnswerDtoToSongEntryMapper {
    @Override
    public SongEntry map(SongEntryWithCorrectAnswerDto data) {
        return SongEntry.builder()
                .data(data.getData())
                .answers(Arrays.stream(data.getAnswers()).toList())
                .correctAnswerIdx(data.getCorrectAnswerIdx())
                .build();
    }
}
