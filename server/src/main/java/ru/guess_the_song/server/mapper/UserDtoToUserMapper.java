package ru.guess_the_song.server.mapper;

import ru.guess_the_song.core.dto.UserDto;
import ru.guess_the_song.server.entity.User;

public interface UserDtoToUserMapper extends Mapper<UserDto, User> {
}
