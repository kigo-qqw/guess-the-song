package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class GiveAnswerDto extends RequestDto {
    @NonNull
    private UUID gameId;
    @NonNull
    private UUID userId;
    private int answerId;
}
