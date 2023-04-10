package ru.guess_the_song.server.mapper;

public interface Mapper<RawDataT, MappedDataT> {
    MappedDataT map(RawDataT data);
}
