package ru.guess_the_song.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.guess_the_song.server.mapper.MusicPackDtoToMusicPackMapper;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackDtoMapper;
import ru.guess_the_song.server.mapper.MusicPackToMusicPackWithCorrectAnswersDtoMapper;
import ru.guess_the_song.server.mapper.MusicPackWithCorrectAnswersDtoToMusicPackMapper;
import ru.guess_the_song.server.mapper.impl.MusicPackDtoToMusicPackMapperImpl;
import ru.guess_the_song.server.mapper.impl.MusicPackToMusicPackDtoMapperImpl;
import ru.guess_the_song.server.mapper.impl.MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl;
import ru.guess_the_song.server.mapper.impl.MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl;
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
    public MusicPackDtoToMusicPackMapper musicPackDtoToMusicPackMapper() {
        return new MusicPackDtoToMusicPackMapperImpl();
    }

    @Bean
    public MusicPackToMusicPackWithCorrectAnswersDtoMapper musicPackToMusicPackWithCorrectAnswersDtoMapper() {
        return new MusicPackToMusicPackWithCorrectAnswersDtoMapperImpl();
    }

    @Bean
    public MusicPackWithCorrectAnswersDtoToMusicPackMapper musicPackWithCorrectAnswersDtoToMusicPackMapper() {
        return new MusicPackWithCorrectAnswersDtoToMusicPackMapperImpl();
    }
}
