package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.CreateUserDto;
import ru.guess_the_song.core.dto.CreateUserResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.core.dto.UserDto;
import ru.guess_the_song.server.controller.CreateUserController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.UserRepository;
import ru.guess_the_song.server.repository.impl.UserRepositoryImpl;

@Slf4j
public class CreateUserControllerImpl implements CreateUserController {
    private final UserRepository userRepository;

    public CreateUserControllerImpl() {
        this.userRepository = new UserRepositoryImpl();  // FIXME: 12.04.2023
    }

    @Override
    public Result<CreateUserResponseDto> request(CreateUserDto request) {
        User user = new User(request.getUsername());
        this.userRepository.save(user);
        log.error("User from repository: {}", this.userRepository.findById(user.getId()));

        return Result.of(CreateUserResponseDto.builder().user(UserDto.builder().uuid(user.getId()).username(user.getUsername()).build()).build());
    }
}
