package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@ToString(callSuper = true)
public class PlayerDto extends EntityDto {
    @NonNull
    private UserDto user;
    private int points;
}
