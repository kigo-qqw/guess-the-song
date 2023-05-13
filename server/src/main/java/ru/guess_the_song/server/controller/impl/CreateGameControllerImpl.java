package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.CreateGameDto;
import ru.guess_the_song.core.dto.CreateGameResponseDto;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.server.controller.CreateGameController;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.GameToGameDtoMapper;
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
    private final GameToGameDtoMapper gameToGameDtoMapper;
    private final MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper;

    public CreateGameControllerImpl(
            GameService gameService,
            UserService userService,
            MusicPackService musicPackService,
            GameToGameDtoMapper gameToGameDtoMapper,
            MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper
    ) {
        this.gameService = gameService;
        this.userService = userService;
        this.musicPackService = musicPackService;
        this.gameToGameDtoMapper = gameToGameDtoMapper;
        this.musicPackWithCorrectAnswersDtoToMusicPackMapper = musicPackWithCorrectAnswersDtoToMusicPackMapper;
    }

    @Override
    public void request(Session session, CreateGameDto request) {
        Optional<User> optionalUser = this.userService.get(request.getInitiatorId());
        if (optionalUser.isEmpty()) return;
        User user = optionalUser.get();

        Optional<MusicPack> optionalMusicPack = this.musicPackService.create(
                this.musicPackWithCorrectAnswersDtoToMusicPackMapper.map(request.getMusicPack()));
        if (optionalMusicPack.isEmpty()) return;
        MusicPack musicPack = optionalMusicPack.get();
        log.debug("{}", request.getMusicPack());
        log.debug("{}", musicPack);

        Optional<Game> optionalGame = this.gameService.create(user, musicPack, session);
        if (optionalGame.isEmpty()) return;
        Game game = optionalGame.get();

        GameDto gameDto = this.gameToGameDtoMapper.map(game);

        session.send(CreateGameResponseDto.builder().game(gameDto).build());
    }
}