package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class GameDto extends EntityDto {
    @NonNull
    private UUID uuid;
    @NonNull
    private UserDto leader;
    @NonNull
    private MusicPackDto musicPack;
}