package ru.guess_the_song.server.service.impl;

import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.PlayerRepository;
import ru.guess_the_song.server.service.PlayerService;

import java.util.Optional;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> create(User user) {
        return Optional.of(this.playerRepository.save(Player.builder().user(user).build()));
    }

}
