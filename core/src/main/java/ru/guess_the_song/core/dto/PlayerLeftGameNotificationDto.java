package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class PlayerLeftGameNotificationDto extends ServerMessageDto {
    @NonNull
    private PlayerDto player;
}
