package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
@Getter
public class StartGameNotificationDto extends ServerMessageDto {
    @NonNull
    private GameDto gameDto;
}
