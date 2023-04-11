package ru.guess_the_song.server.repository.impl;

import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Song;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.repository.MusicPackRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MusicPackRepositoryImpl implements MusicPackRepository {
    @Override
    public long count() {
        return 0; // FIXME: 10.04.2023
    }

    @Override
    public void delete(MusicPack entity) {
        // FIXME: 10.04.2023
    }

    @Override
    public void deleteAll() {
        // FIXME: 10.04.2023
    }

    @Override
    public void deleteAll(Iterable<MusicPack> entities) {
        // FIXME: 10.04.2023
    }

    @Override
    public void deleteAllById(Iterable<MusicPack> ids) {
        // FIXME: 10.04.2023
    }

    @Override
    public void deleteById(UUID uuid) {
        // FIXME: 10.04.2023
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;  // FIXME: 10.04.2023
    }

    @Override
    public Iterable<MusicPack> findAll() {
        return null;  // FIXME: 10.04.2023
    }

    @Override
    public Iterable<MusicPack> findAllById(Iterable<MusicPack> ids) {
        return null;  // FIXME: 10.04.2023
    }

    @Override
    public Optional<MusicPack> findById(UUID uuid) {
        return Optional.of(
                MusicPack.builder()
                        .uuid(uuid)
                        .songs(
                                List.of(
                                        SongEntry.builder()
                                                .uuid(UUID.randomUUID())
                                                .song(Song.builder().uuid(UUID.randomUUID()).build())
                                                .answers(List.of(
                                                        "[1] - [1]",
                                                        "[1] - [2]",
                                                        "[1] - [3]",
                                                        "[1] - [4]")
                                                )
                                                .correctAnswerIdx(0)
                                                .build(),
                                        SongEntry.builder()
                                                .uuid(UUID.randomUUID())
                                                .song(Song.builder().uuid(UUID.randomUUID()).build())
                                                .answers(List.of(
                                                        "[2] - [1]",
                                                        "[2] - [2]",
                                                        "[2] - [3]",
                                                        "[2] - [4]")
                                                )
                                                .correctAnswerIdx(0)
                                                .build()
                                )
                        ).build()
        );
        // return Optional.empty(); // FIXME: 10.04.2023
    }

    @Override
    public <S extends MusicPack> S save(S entity) {
        return null; // FIXME: 10.04.2023
    }

    @Override
    public <S extends MusicPack> Iterable<S> saveAll(Iterable<S> entities) {
        return null; // FIXME: 10.04.2023
    }
}
