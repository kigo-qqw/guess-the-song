package ru.guess_the_song.server.entity;


import jakarta.persistence.Entity;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class MusicPack extends BaseEntity {
//    private List<SongEntry> songs;
    @Builder private MusicPack(UUID id){
        super(id);
    }
}
