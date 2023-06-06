package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PlayerJoinGameNotificationDto extends EntityDto {
    @NonNull
    private PlayerDto player;
}
