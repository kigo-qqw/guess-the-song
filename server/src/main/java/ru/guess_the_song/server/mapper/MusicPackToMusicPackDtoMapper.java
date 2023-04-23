package ru.guess_the_song.server.mapper;

import ru.guess_the_song.core.dto.GetMusicPackResponseDto;
import ru.guess_the_song.core.dto.MusicPackDto;
import ru.guess_the_song.server.entity.MusicPack;

public interface MusicPackToMusicPackDtoMapper extends Mapper<MusicPack, MusicPackDto> {

}
