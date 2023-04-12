package ru.guess_the_song.server.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class MusicPack extends BaseEntity {
//    private List<SongEntry> songs;
}
