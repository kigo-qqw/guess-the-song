package ru.guess_the_song.server.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.core.dto.JoinGameDto;
import ru.guess_the_song.server.controller.JoinGameController;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.mapper.UserDtoToUserMapper;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.service.GameService;

//@Slf4j
//@Component
//public class JoinGameControllerImpl implements JoinGameController {
//    private final GameService gameService;
//    private final UserDtoToUserMapper userDtoToUserMapper;
//
//    public JoinGameControllerImpl(GameService gameService, UserDtoToUserMapper userDtoToUserMapper) {
//        this.gameService = gameService;
//        this.userDtoToUserMapper = userDtoToUserMapper;
//    }
//
//    @Override
//    public Result<JoinGameResponseDto> request(Session session, JoinGameDto request) {
//        User user = this.userDtoToUserMapper.map(request.getUser());  // FIXME: 26.04.2023 validate in userService?
//        this.gameService.join(request.getGameId(), user, session);
//        return Result.of(JoinGameResponseDto.builder().build());  // FIXME: 26.04.2023 idk
//    }
//}

@Slf4j
@Component
public class JoinGameControllerImpl implements JoinGameController {
    private final GameService gameService;
    private final UserDtoToUserMapper userDtoToUserMapper;

    public JoinGameControllerImpl(GameService gameService, UserDtoToUserMapper userDtoToUserMapper) {
        this.gameService = gameService;
        this.userDtoToUserMapper = userDtoToUserMapper;
    }

    @Override
    public void request(Session session, JoinGameDto request) {
        User user = this.userDtoToUserMapper.map(request.getUser());  // FIXME: 26.04.2023 validate in userService?
        this.gameService.join(request.getGameId(), user, session);
//        session.send(JoinGameResponseDto.builder().build());  // FIXME: 26.04.2023 idk
    }
}