package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class EndRoundDto extends RequestDto {
    @NonNull
    private UUID gameId;
    private @NonNull String @NonNull [] answers;
    private int correctAnswerId;
}