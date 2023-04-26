package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateGameDto extends RequestDto {
    @NonNull
    private UserDto initiator;
    @NonNull
    private MusicPackWithCorrectAnswersDto musicPack;
}
