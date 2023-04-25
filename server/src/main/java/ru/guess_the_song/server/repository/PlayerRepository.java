package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.Player;

import java.util.UUID;

public interface PlayerRepository extends Repository<Player, UUID> {
    <S extends Player> S save(S entity);
}
