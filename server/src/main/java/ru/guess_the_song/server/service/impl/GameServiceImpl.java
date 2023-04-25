package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.PlayerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<Game, Map<Player, Session>> activeGames;

    private final PlayerService playerService;

    public GameServiceImpl(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.activeGames = new HashMap<>();
    }

    @Override
    public Optional<Game> create(User initiator, MusicPack musicPack) {
        Optional<Player> optionalLeader = this.playerService.create(initiator);
        if (optionalLeader.isPresent()) {
            Player leader = optionalLeader.get();
            Game game = Game.builder().leader(leader).musicPack(musicPack).build();
            game.getPlayers().add(leader);
            this.gameRepository.save(game);
            return Optional.of(game);
        }
        return Optional.empty();
    }

    @Override
    public void join(UUID gameId, User user, Session session) {
        Optional<Game> game = this.gameRepository.findById(gameId);
        if (game.isPresent()) {
            if (this.activeGames.containsKey(game.get())) {
                this.activeGames.get(game.get()).put(Player.builder().user(user).build(), session);
            }
        }
    }

    @Override
    public void start(UUID gameId, User user) {
        Optional<Game> game = this.gameRepository.findById(gameId);
        if (game.isPresent() && game.get().getLeader().getUser().equals(user)) {
            this.executor.submit(() -> {
                // FIXME: 25.04.2023

                boolean run = true;
                int musicPackIdx = 0;
                while (run) {
                    MusicPack musicPack = game.get().getMusicPack();
                }
            });
        }
    }
}
