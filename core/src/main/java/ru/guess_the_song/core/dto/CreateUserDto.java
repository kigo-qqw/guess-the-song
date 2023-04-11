package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserDto extends Request {
    private String username;
    private String password;
}
