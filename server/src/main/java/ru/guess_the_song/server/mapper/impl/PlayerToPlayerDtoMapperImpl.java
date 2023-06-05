package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.PlayerDto;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;

public class PlayerToPlayerDtoMapperImpl implements PlayerToPlayerDtoMapper {

    @Override
    public PlayerDto map(Player data) {
        return PlayerDto.builder()
                .userId(data.getUser().getId())
                .points(data.getPoints())
                .build();
    }
}
