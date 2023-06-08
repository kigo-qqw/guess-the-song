package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.PlayerRepository;
import ru.guess_the_song.server.service.PlayerService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> create(Game game, User user) {
        return Optional.of(this.playerRepository.save(Player.builder().game(game).user(user).build()));
    }

    @Override
    public Optional<Player> get(Game game, User user) {
        return this.playerRepository.findByGameAndUser(game, user);
    }

    @Override
    public Optional<Player> get(UUID id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public void increasePoints(Player player, int points) {
        player.setPoints(player.getPoints() + points);
        this.playerRepository.save(player);
    }
}
