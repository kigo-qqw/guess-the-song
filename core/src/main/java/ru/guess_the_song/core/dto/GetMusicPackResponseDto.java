package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class GetMusicPackResponseDto extends ResponseDto {
    private UUID uuid;
    private List<SongEntryDto> songs;
}