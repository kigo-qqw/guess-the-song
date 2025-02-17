package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetPlayerResponseDto extends ResponseDto {
    private PlayerDto player;
}
