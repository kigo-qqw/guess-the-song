package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.*;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackDtoMapper;
import ru.guess_the_song.server.mapper.MusicPackWithCorrectAnswersDtoToMusicPackMapper;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.MusicPackService;
import ru.guess_the_song.server.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class CreateGameControllerImpl implements CreateGameController {
    private final GameService gameService;
    private final UserService userService;
    private final MusicPackService musicPackService;
    private final MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper;

    public CreateGameControllerImpl(
            GameService gameService,
            UserService userService,
            MusicPackService musicPackService,
            MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper
    ) {
        this.gameService = gameService;
        this.userService = userService;
        this.musicPackService = musicPackService;
        this.musicPackWithCorrectAnswersDtoToMusicPackMapper = musicPackWithCorrectAnswersDtoToMusicPackMapper;
    }

    @Override
    public Result<CreateGameResponseDto> request(CreateGameDto request) {
        Optional<User> user = this.userService.get(request.getInitiator().getUuid());
        MusicPack mp = this.musicPackWithCorrectAnswersDtoToMusicPackMapper.map(request.getMusicPack());
        Optional<MusicPack> musicPack = this.musicPackService.create(List.of());  // FIXME: 23.04.2023
        if (user.isPresent() && musicPack.isPresent()) {
            Optional<Game> game = this.gameService.create(user.get(), musicPack.get());
            if (game.isPresent()){
//                GameDto gameDto = GameDto.builder().leader(UserDto.builder().uuid(user.get().getId()).build()).build();
//                return Result.of(CreateGameResponseDto.builder().game());
            }
        }
        return Result.empty();
    }
}
