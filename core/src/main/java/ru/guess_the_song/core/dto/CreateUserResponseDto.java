package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateUserResponseDto extends ResponseDto {
    @NonNull
    private UserDto user;
}
