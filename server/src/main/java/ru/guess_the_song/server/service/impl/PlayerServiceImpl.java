package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.PlayerRepository;
import ru.guess_the_song.server.service.PlayerService;

import java.util.Optional;

@Slf4j
@Component
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> create(User user) {
        return Optional.of(this.playerRepository.save(Player.builder().user(user).build()));
    }

    @Override
    public Optional<Player> get(User user) {
        return this.playerRepository.findByUser(user);
    }
}
