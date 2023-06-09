package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class StartGameDto extends RequestDto {
    @NonNull
    private UUID gameId;
    @NonNull
    private UUID userId;
}
