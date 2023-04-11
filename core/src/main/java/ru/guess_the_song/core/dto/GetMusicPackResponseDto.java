package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetMusicPackResponseDto extends ResponseDto {
    private UUID uuid;
    private List<SongEntryDto> songs;
}