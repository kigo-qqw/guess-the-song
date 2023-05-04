package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.core.dto.CreateUserResponseDto;
import ru.guess_the_song.server.controller.CreateUserController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.UserToUserDtoMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class CreateUserControllerImpl implements CreateUserController {
    private final UserService userService;
    private final UserToUserDtoMapper userToUserDtoMapper;

    @Autowired
    public CreateUserControllerImpl(UserService userService, UserToUserDtoMapper userToUserDtoMapper) {
        this.userService = userService;
        this.userToUserDtoMapper = userToUserDtoMapper;
    }

    @Override
    public void request(Session session, CreateUserDto request) {
        Optional<User> optionalUser = this.userService.create(request.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.send(CreateUserResponseDto.builder().user(this.userToUserDtoMapper.map(user)));
        }
    }
}