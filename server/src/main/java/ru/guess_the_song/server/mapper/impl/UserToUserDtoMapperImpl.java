package ru.guess_the_song.server.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.UserDto;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.UserToUserDtoMapper;

@Slf4j
@Component
public class UserToUserDtoMapperImpl implements UserToUserDtoMapper {
    @Override
    public UserDto map(User data) {
        return UserDto.builder()
                .id(data.getId())
                .username(data.getUsername())
                .build();
    }
}
