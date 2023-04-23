package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface MusicPackRepository extends Repository<MusicPack, UUID>{
    <S extends MusicPack> S save(S entity);
}
