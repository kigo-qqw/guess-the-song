package ru.guess_the_song.server.mapper;

import ru.guess_the_song.core.dto.MusicPackWithCorrectAnswersDto;
import ru.guess_the_song.server.entity.MusicPack;

public interface MusicPackToMusicPackWithCorrectAnswersDtoMapper extends Mapper<MusicPack, MusicPackWithCorrectAnswersDto> {
}
