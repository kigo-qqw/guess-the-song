package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateGameDto extends RequestDto {
    private UserDto initiator;
    private MusicPackWithCorrectAnswersDto musicPack;
}
