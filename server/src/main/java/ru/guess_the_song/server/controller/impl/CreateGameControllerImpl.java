package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.CreateGameDto;
import ru.guess_the_song.core.dto.CreateGameResponseDto;
import ru.guess_the_song.core.dto.GameDto;
import ru.guess_the_song.core.dto.Status;
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
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Optional<MusicPack> optionalMusicPack = this.musicPackService.create(
                    this.musicPackWithCorrectAnswersDtoToMusicPackMapper.map(request.getMusicPack()));
            if (optionalMusicPack.isPresent()) {
                MusicPack musicPack = optionalMusicPack.get();

                Optional<Game> optionalGame = this.gameService.create(user, musicPack, session);
                if (optionalGame.isPresent()) {
                    Game game = optionalGame.get();

                    GameDto gameDto = this.gameToGameDtoMapper.map(game);

                    session.send(CreateGameResponseDto.builder()
                            .status(Status.OK)
                            .game(gameDto)
                            .build());
                    return;
                }
            }
        }
        session.send(CreateGameResponseDto.builder().status(Status.ERROR).build());
    }
}