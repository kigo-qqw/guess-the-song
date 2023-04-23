package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.SongEntry;
import ru.guess_the_song.server.repository.MusicPackRepository;
import ru.guess_the_song.server.service.MusicPackService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MusicPackServiceImpl implements MusicPackService {
    private final MusicPackRepository musicPackRepository;

    public MusicPackServiceImpl(MusicPackRepository musicPackRepository) {
        this.musicPackRepository = musicPackRepository;
    }

    @Override
    public Optional<MusicPack> create(List<SongEntry> songs) {
        return Optional.of(this.musicPackRepository.save(MusicPack.builder().build()));
    }
}
