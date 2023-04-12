package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Bag;
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
//@Table(name = "SONG_ENTRY")
public class SongEntry extends BaseEntity {
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "song_id", referencedColumnName = "uuid")
//    private Song song;
//    @ElementCollection(targetClass = String.class)
//    private List<String> answers;
    private int correctAnswerIdx;
}
