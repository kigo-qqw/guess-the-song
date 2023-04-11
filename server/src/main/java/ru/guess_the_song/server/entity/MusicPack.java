package ru.guess_the_song.server.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
public class MusicPack extends EntityEE {
    @Getter
    @Setter
    private UUID uuid;
    @Getter
    @Setter
    private List<SongEntry> songs;
}
