package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserResponseDto extends ResponseDto {
    private UserDto user;
}
