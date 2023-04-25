package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryWithCorrectAnswerDtoMapper;

import java.util.List;

@Slf4j
@Component
public class SongEntryToSongEntryWithCorrectAnswerDtoMapperImpl implements SongEntryToSongEntryWithCorrectAnswerDtoMapper {
    @Override
    public SongEntryWithCorrectAnswerDto map(SongEntry data) {
        return SongEntryWithCorrectAnswerDto.builder()
                .data(data.getData())
                .answers(List.of())  // FIXME: 25.04.2023
                .correctAnswerIdx(data.getCorrectAnswerIdx())
                .build();
    }
}
