package ru.guess_the_song.server.entity;


import jakarta.persistence.*;
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
@Table(name = "MusicPackTable")
@EqualsAndHashCode(callSuper = true)
public class MusicPack extends BaseEntity {
    //    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SongEntry> songs;

    @Builder
    private MusicPack(UUID id, List<SongEntry> songs) {
        super(id);
        this.songs = songs;
    }
}
