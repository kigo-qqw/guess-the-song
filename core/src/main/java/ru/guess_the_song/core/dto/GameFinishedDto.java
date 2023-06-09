package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
public class GameFinishedDto extends ServerMessageDto {
    @NonNull
    private GameDto game;
    @NonNull
    private PlayerDto @NonNull [] winners;
}
