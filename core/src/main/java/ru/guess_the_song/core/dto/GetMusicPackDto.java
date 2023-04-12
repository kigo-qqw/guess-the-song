package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Builder
@ToString
public class GetMusicPackDto extends RequestDto {
    private UUID uuid;
}