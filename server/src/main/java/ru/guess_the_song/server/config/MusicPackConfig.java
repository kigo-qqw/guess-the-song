package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.guess_the_song.server.mapper.*;
import ru.guess_the_song.server.mapper.impl.*;
import ru.guess_the_song.server.repository.MusicPackRepository;
import ru.guess_the_song.server.repository.impl.MusicPackRepositoryImpl;
import ru.guess_the_song.server.service.MusicPackService;
import ru.guess_the_song.server.service.impl.MusicPackServiceImpl;

@Configuration
public class MusicPackConfig {
    @Bean
    public MusicPackService musicPackService() {
        return new MusicPackServiceImpl(musicPackRepository());
    }

    @Bean
    public MusicPackRepository musicPackRepository() {
        return new MusicPackRepositoryImpl();
    }

    @Bean
    public MusicPackToMusicPackDtoMapper musicPackToMusicPackDtoMapper() {
        return new MusicPackToMusicPackDtoMapperImpl();
    }

    @Bean
    public MusicPackToMusicPackWithCorrectAnswersDtoMapper musicPackToMusicPackWithCorrectAnswersDtoMapper() {
        return new MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl();
    }

    @Bean
    public MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper() {
        return new MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl(songEntryWithCorrectAnswerDtoToSongEntryMapper());
    }

    @Bean
    public SongEntryWithCorrectAnswerDtoToSongEntryMapper songEntryWithCorrectAnswerDtoToSongEntryMapper() {
        return new SongEntryWithCorrectAnswerDtoToSongEntryMapperImpl();
    }

    @Bean
    public SongEntryToSongEntryDtoMapper songEntryToSongEntryDtoMapper() {
        return new SongEntryToSongEntryDtoMapperImpl();
    }

    @Bean
    public SongEntryToSongEntryWithCorrectAnswerDtoMapper songEntryToSongEntryWithCorrectAnswerDtoMapper() {
        return new SongEntryToSongEntryWithCorrectAnswerDtoMapperImpl();
    }
}
