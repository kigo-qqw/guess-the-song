package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString(callSuper = true)
public class PlayerLeaveDto extends ServerMessageDto {
    @NonNull private GameDto game;
//    @NonNull private PlayerDto player;
}
