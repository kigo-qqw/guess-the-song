package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.SongEntryDto;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryDtoMapper;


@Slf4j
@Component
public class SongEntryToSongEntryDtoMapperImpl implements SongEntryToSongEntryDtoMapper {

    @Override
    public SongEntryDto map(SongEntry data) {
        return SongEntryDto.builder()
                .data(data.getData())
                .answers(data.getAnswers().toArray(String[]::new))
                .build();
    }
}
