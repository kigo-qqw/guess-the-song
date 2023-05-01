package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.mapper.UserToUserDtoMapper;

public class GameToGameDtoMapperImpl implements GameToGameDtoMapper {
    private final UserToUserDtoMapper userToUserDtoMapper;


    public GameToGameDtoMapperImpl(UserToUserDtoMapper userToUserDtoMapper) {
        this.userToUserDtoMapper = userToUserDtoMapper;
    }

    @Override
    public GameDto map(Game data) {
        return GameDto.builder()
                .uuid(data.getId())
                .leader(this.userToUserDtoMapper.map(data.getLeader().getUser()))
                .build();
    }
}
