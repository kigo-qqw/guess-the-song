package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class GameDto extends EntityDto {
    private UUID uuid;
    private UserDto leader;
    private MusicPackDto musicPack;
}