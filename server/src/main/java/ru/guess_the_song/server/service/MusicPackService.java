package ru.guess_the_song.server.service;

import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.SongEntry;

import java.util.List;
import java.util.Optional;

public interface MusicPackService {
    Optional<MusicPack> create(List<SongEntry> songs);
}
