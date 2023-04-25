package ru.guess_the_song.server.mapper;

import ru.guess_the_song.core.dto.SongEntryWithCorrectAnswerDto;
import ru.guess_the_song.server.entity.SongEntry;

public interface SongEntryWithCorrectAnswerDtoToSongEntryMapper extends Mapper<SongEntryWithCorrectAnswerDto, SongEntry> {
}
