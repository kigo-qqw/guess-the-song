package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.PlayerDto;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;
import ru.guess_the_song.server.mapper.UserToUserDtoMapper;

public class PlayerToPlayerDtoMapperImpl implements PlayerToPlayerDtoMapper {
    private final UserToUserDtoMapper userToUserDtoMapper;

    public PlayerToPlayerDtoMapperImpl(UserToUserDtoMapper userToUserDtoMapper) {
        this.userToUserDtoMapper = userToUserDtoMapper;
    }

    @Override
    public PlayerDto map(Player data) {
        return PlayerDto.builder()
                .user(this.userToUserDtoMapper.map(data.getUser()))
                .points(data.getPoints())
                .build();
    }
}
