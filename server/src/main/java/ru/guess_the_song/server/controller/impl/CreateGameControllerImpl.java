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
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.MusicPackService;
import ru.guess_the_song.server.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class CreateGameControllerImpl implements CreateGameController {
    private final GameService gameService;
    private final UserService userService;
    private final MusicPackService musicPackService;
    private final MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper;
    private final MusicPackToMusicPackDtoMapper musicPackToMusicPackDtoMapper;

    public CreateGameControllerImpl(
            GameService gameService,
            UserService userService,
            MusicPackService musicPackService,
            MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper,
            MusicPackToMusicPackDtoMapper musicPackToMusicPackDtoMapper
    ) {
        this.gameService = gameService;
        this.userService = userService;
        this.musicPackService = musicPackService;
        this.musicPackWithCorrectAnswersDtoToMusicPackMapper = musicPackWithCorrectAnswersDtoToMusicPackMapper;
        this.musicPackToMusicPackDtoMapper = musicPackToMusicPackDtoMapper;
    }

    @Override
    public Result<CreateGameResponseDto> request(Session session, CreateGameDto request) {
        if (request.getInitiator() == null) return Result.empty();

        Optional<User> optionalUser = this.userService.get(request.getInitiator().getUuid());
        if (optionalUser.isEmpty()) return Result.empty();
        User user = optionalUser.get();

        Optional<MusicPack> optionalMusicPack = this.musicPackService.create(
                this.musicPackWithCorrectAnswersDtoToMusicPackMapper.map(request.getMusicPack()));
        if (optionalMusicPack.isEmpty()) return Result.empty();
        MusicPack musicPack = optionalMusicPack.get();
        log.debug("{}", request.getMusicPack());
        log.debug("{}", musicPack);

        Optional<Game> optionalGame = this.gameService.create(user, musicPack);
        if (optionalGame.isEmpty()) return Result.empty();
        Game game = optionalGame.get();

        GameDto gameDto = GameDto.builder()
                .leader(request.getInitiator())
                .musicPack(this.musicPackToMusicPackDtoMapper.map(musicPack))
                .build();
        return Result.of(CreateGameResponseDto.builder().game(gameDto).build());

    }
}
