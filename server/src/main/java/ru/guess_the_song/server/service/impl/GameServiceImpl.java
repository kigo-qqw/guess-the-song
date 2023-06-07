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

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final GameRunnerFactory gameRunnerFactory;
    //    private final Map<UUID, Map<Player, Session>> activeGames;
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
        log.debug("create call");
        Optional<Player> optionalLeader = this.playerService.create(initiator);
        if (optionalLeader.isPresent()) {
            Player leader = optionalLeader.get();
            Game game = Game.builder().leader(leader).musicPack(musicPack).build();

//            game.getPlayers().add(leader);


            this.gameRepository.save(game);
//            this.activeGames.put(game.getId(), new HashMap<>());
            this.activeGames.put(game.getId(), this.gameRunnerFactory.createGameRunner(game));

            join(game.getId(), initiator, session);

//            log.debug(game.toString());
//            log.debug("ttt" + this.gameRepository.findById(game.getId()));
            return Optional.of(game);
        }
        return Optional.empty();
    }

    @Override
    public void join(UUID gameId, User user, Session session) {
        log.debug("join call");
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            log.debug("present");
            Game game = optionalGame.get();
            if (this.activeGames.containsKey(game.getId())) {
                Optional<Player> optionalPlayer = this.playerService.create(user);
                if (optionalPlayer.isPresent()) {
                    Player player = optionalPlayer.get();
                    game.getPlayers().add(player);
//                    this.activeGames.get(game.getId()).put(player, session);
                    this.activeGames.get(game.getId()).addPlayer(player, session);


                    log.debug("added: " + player);
                    log.debug(String.valueOf(game));

//                    this.gameRepository.save(game);
                }
            } else throw new RuntimeException("xdd");
        }
    }

    @Override
    public void start(UUID gameId, User user) {
        log.debug("start call");
        log.debug("gameId=" + gameId);
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            log.debug(game.getLeader().getUser().toString());
            log.debug(user.toString());
            if (game.getLeader().getUser().equals(user)) {
                log.debug("xddddd1");
//                this.executor.submit(this.gameRunnerFactory.createGameRunner(
//                        game,
//                        this.activeGames.get(game)
//                ));
//                new Thread(this.gameRunnerFactory.createGameRunner(
//                        game
//                )).start();
                new Thread(this.activeGames.get(game.getId())).start();
            }
        }
    }

    @Override
    public void giveAnswer(UUID gameId, User user, int answerId) {
        Optional<Player> optionalPlayer = this.playerService.get(user);
        if (optionalPlayer.isEmpty()) return;
        this.activeGames.get(gameId).giveAnswer(optionalPlayer.get(), answerId);
    }

    @Override
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @Override
    public List<Game> getAllActive() {
        return this.gameRepository.findAllActive();
    }

//    @Override
//    public void notifyPlayersNewPlayerJoined(UUID gameId, User user) {
//        Optional<Player> optionalPlayer = this.playerService.get(user);
//        if (optionalPlayer.isEmpty()) return;
//        this.activeGames.get(gameId)
//        this.activeGames.get(gameId).giveAnswer(optionalPlayer.get(), answerId);
//    }
}
