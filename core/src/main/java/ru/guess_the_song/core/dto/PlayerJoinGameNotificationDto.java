package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class PlayerJoinGameNotificationDto extends ServerMessageDto {
    @NonNull
    private PlayerDto player;
}
