package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.GameService;

import java.util.Optional;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> create(User initiator, MusicPack musicPack) {
        Game game = new Game();
        game.getUsers().add(new Player());
        return Optional.empty();
    }
}
