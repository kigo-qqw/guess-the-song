package ru.guess_the_song.server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@ToString
public class MusicPack extends BaseEntity {
    @OneToMany
    private List<SongEntry> songs;

    @Builder
    private MusicPack(UUID id, List<SongEntry> songs) {
        super(id);
        this.songs = songs;
    }
}
