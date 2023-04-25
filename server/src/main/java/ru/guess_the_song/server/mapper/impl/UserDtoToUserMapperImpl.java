package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.UserDto;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.UserDtoToUserMapper;

@Slf4j
@Component
public class UserDtoToUserMapperImpl implements UserDtoToUserMapper {
    @Override
    public User map(UserDto data) {
        return User.builder()
                .id(data.getUuid())
                .username(data.getUsername())
                .build();
    }
}
