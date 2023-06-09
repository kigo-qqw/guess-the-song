package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.game.GameRunner;
import ru.guess_the_song.server.game.GameRunnerFactory;
import ru.guess_the_song.server.net.Session;
import ru.guess_the_song.server.repository.GameRepository;
import ru.guess_the_song.server.service.GameService;
import ru.guess_the_song.server.service.PlayerService;

import java.net.Socket;
import java.util.*;

@Slf4j
@Component
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameRunnerFactory gameRunnerFactory;
    private final Map<UUID, GameRunner> activeGames;
    private final PlayerService playerService;

    public GameServiceImpl(GameRepository gameRepository, GameRunnerFactory gameRunnerFactory, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.gameRunnerFactory = gameRunnerFactory;
        this.playerService = playerService;
        this.activeGames = new HashMap<>();
    }

    @Override
    public Optional<Game> create(User initiator, MusicPack musicPack, Session session) {
        Game game = Game.builder().musicPack(musicPack).build();
        this.gameRepository.save(game);

        Optional<Player> optionalLeader = this.playerService.create(game, initiator);

        if (optionalLeader.isPresent()) {
            game.setLeader(optionalLeader.get());
            this.gameRepository.save(game);

            this.activeGames.put(game.getId(), this.gameRunnerFactory.createGameRunner(game));
            this.activeGames.get(game.getId()).addPlayer(optionalLeader.get(), session);
            return Optional.of(game);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Game> join(UUID gameId, User user, Session session) {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            if (this.activeGames.containsKey(game.getId())) {
                Optional<Player> optionalPlayer = this.playerService.create(game, user);
                if (optionalPlayer.isPresent()) {
                    Player player = optionalPlayer.get();
                    game.getPlayers().add(player);
                    this.activeGames.get(game.getId()).addPlayer(player, session);

                    this.gameRepository.save(game);
                    return Optional.of(game);
                }
            } else throw new RuntimeException("xdd");
        }
        return Optional.empty();
    }

    @Override
    public void start(UUID gameId, User user) {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();

            if (game.getLeader().getUser().equals(user)) {
                new Thread(this.activeGames.get(game.getId())).start();
            }
        }
    }

    @Override
    public void giveAnswer(UUID gameId, Player player, int answerId) {
        this.activeGames.get(gameId).giveAnswer(player, answerId);
    }

    @Override
    public void notifySocketClose(Socket socket) {
        this.activeGames.forEach((key, value) -> {
            value.notifySocketClose(socket);
        });
    }

    @Override
    public List<Game> getAllActive() {
        return this.gameRepository.findAllActive();
    }

    @Override
    public Optional<Game> get(UUID gameId) {
        return this.gameRepository.findById(gameId);
    }

    @Override
    public void leave(UUID gameId, User user) {
        Optional<Game> optionalGame = get(gameId);
        if (optionalGame.isEmpty()) return;

        Optional<Player> optionalPlayer = this.playerService.get(optionalGame.get(), user);
        if (optionalPlayer.isEmpty()) return;

        this.activeGames.get(gameId).removePlayer(optionalPlayer.get());
    }
}
