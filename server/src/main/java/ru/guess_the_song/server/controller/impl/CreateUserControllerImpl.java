package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.core.dto.CreateUserResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.core.dto.UserDto;
import ru.guess_the_song.server.controller.CreateUserController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class CreateUserControllerImpl implements CreateUserController {
    private final UserService userService;

    @Autowired
    public CreateUserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<CreateUserResponseDto> request(CreateUserDto request) {
        Optional<User> optionalUser = this.userService.create(request.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return Result.of(CreateUserResponseDto.builder()
                    .user(UserDto.builder()
                            .uuid(user.getId())
                            .username(user.getUsername())
                            .build())
                    .build());
        }
        return Result.empty();
    }
}
