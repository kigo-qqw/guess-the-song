package ru.guess_the_song.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.guess_the_song.core.dto.GetMusicPackDto;
import ru.guess_the_song.core.dto.GetMusicPackResponseDto;
import ru.guess_the_song.core.dto.Result;
import ru.guess_the_song.server.mapper.MusicPackMapper;
import ru.guess_the_song.server.mapper.impl.MusicPackMapperImpl;
import ru.guess_the_song.server.repository.MusicPackRepository;
import ru.guess_the_song.server.repository.impl.MusicPackRepositoryImpl;
import ru.guess_the_song.server.service.MusicPackService;

@Slf4j
public class MusicPackServiceImpl implements MusicPackService {
    private final MusicPackRepository musicPackRepository;
    private final MusicPackMapper musicPackMapper;

    public MusicPackServiceImpl() {
        this.musicPackRepository = new MusicPackRepositoryImpl(); // FIXME: 10.04.2023
        this.musicPackMapper = new MusicPackMapperImpl();

    }

    @Override
    public Result<GetMusicPackResponseDto> get(GetMusicPackDto request) {
//        Optional<MusicPack> musicPack = this.musicPackRepository.findById(request.getUuid());
//        return musicPack.map(pack -> Result.of(this.musicPackMapper.map(pack))).orElseGet(Result::empty);
        return Result.empty();
    }
}
