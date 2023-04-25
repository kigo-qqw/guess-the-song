package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.SongEntryWithCorrectAnswerDtoToSongEntryMapper;

@Slf4j
@Component
public class SongEntryWithCorrectAnswerDtoToSongEntryMapperImpl implements SongEntryWithCorrectAnswerDtoToSongEntryMapper {
    @Override
    public SongEntry map(SongEntryWithCorrectAnswerDto data) {
        return SongEntry.builder()
                .data(data.getData())
                // FIXME: 25.04.2023
                .correctAnswerIdx(data.getCorrectAnswerIdx())
                .build();
    }
}
