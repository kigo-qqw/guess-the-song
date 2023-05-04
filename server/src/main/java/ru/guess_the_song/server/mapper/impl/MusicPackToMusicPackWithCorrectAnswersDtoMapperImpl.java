package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackWithCorrectAnswersDtoMapper;
import ru.guess_the_song.server.mapper.SongEntryToSongEntryWithCorrectAnswerDtoMapper;

@Slf4j
@Component
public class MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl implements MusicPackToMusicPackWithCorrectAnswersDtoMapper {
    private final SongEntryToSongEntryWithCorrectAnswerDtoMapper songEntryToSongEntryWithCorrectAnswerDtoMapper;

    public MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl(SongEntryToSongEntryWithCorrectAnswerDtoMapper songEntryToSongEntryWithCorrectAnswerDtoMapper) {
        this.songEntryToSongEntryWithCorrectAnswerDtoMapper = songEntryToSongEntryWithCorrectAnswerDtoMapper;
    }

    @Override
    public MusicPackWithCorrectAnswersDto map(MusicPack data) {
        return MusicPackWithCorrectAnswersDto.builder()
                .id(data.getId())
                .songs(data.getSongs().stream().map(songEntryToSongEntryWithCorrectAnswerDtoMapper::map).toArray(SongEntryWithCorrectAnswerDto[]::new))
                .build();
    }
}
