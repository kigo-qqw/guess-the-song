package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class GetMusicPackDto extends RequestDto {
    private UUID uuid;
}