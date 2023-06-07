package ru.guess_the_song.server.controller.impl;

import ru.guess_the_song.core.dto.GetPlayerResponseDto;
import ru.guess_the_song.core.dto.GetUserDto;
import ru.guess_the_song.core.dto.GetUserResponseDto;
import ru.guess_the_song.core.dto.Status;
import ru.guess_the_song.server.controller.GetUserController;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.UserToUserDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

public class GetUserControllerImpl implements GetUserController {
    private final UserService userService;
    private final UserToUserDtoMapper userToUserDtoMapper;

    public GetUserControllerImpl(UserService userService, UserToUserDtoMapper userToUserDtoMapper) {
        this.userService = userService;
        this.userToUserDtoMapper = userToUserDtoMapper;
    }

    @Override
    public void request(Session session, GetUserDto request) {
        Optional<User> optionalUser = this.userService.get(request.getUserId());
        if (optionalUser.isPresent())
            session.send(GetUserResponseDto.builder()
                    .status(Status.OK)
                    .user(this.userToUserDtoMapper.map(optionalUser.get()))
                    .build());
        else
            session.send(GetUserResponseDto.builder()
                    .status(Status.ERROR)
                    .build());

    }
}
