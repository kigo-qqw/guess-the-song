package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryWithCorrectAnswerDtoMapper;

@Slf4j
@Component
public class SongEntryToSongEntryWithCorrectAnswerDtoMapperImpl implements SongEntryToSongEntryWithCorrectAnswerDtoMapper {
    @Override
    public SongEntryWithCorrectAnswerDto map(SongEntry data) {
        return SongEntryWithCorrectAnswerDto.builder()
                .data(data.getData())
                .answers(data.getAnswers().toArray(String[]::new))
                .correctAnswerIdx(data.getCorrectAnswerIdx())
                .build();
    }
}
