package ru.guess_the_song.server.mapper.impl;

import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.PlayerDto;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
import ru.guess_the_song.server.mapper.PlayerToPlayerDtoMapper;

public class GameToGameDtoMapperImpl implements GameToGameDtoMapper {
    private final PlayerToPlayerDtoMapper playerToPlayerDtoMapper;

    public GameToGameDtoMapperImpl(PlayerToPlayerDtoMapper playerToPlayerDtoMapper) {
        this.playerToPlayerDtoMapper = playerToPlayerDtoMapper;
    }

    @Override
    public GameDto map(Game data) {
        return GameDto.builder()
                .id(data.getId())
                .leaderId(data.getLeader().getId())
                .players(data.getPlayers().stream().map(this.playerToPlayerDtoMapper::map).toArray(PlayerDto[]::new))
                .build();
    }
}
