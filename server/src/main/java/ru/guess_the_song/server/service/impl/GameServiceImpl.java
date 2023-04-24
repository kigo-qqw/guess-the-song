package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.GameService;

import java.util.Optional;
import java.util.UUID;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> create(User initiator, MusicPack musicPack) {
        Player leader = Player.builder().user(initiator).build();
        Game game = Game.builder().leader(leader).musicPack(musicPack).build();
        game.getPlayers().add(leader);

        this.gameRepository.save(game);

        return Optional.of(game);
    }

    @Override
    public void start(UUID gameId, User user) {
        Optional<Game> game = this.gameRepository.findById(gameId);
    }
}
