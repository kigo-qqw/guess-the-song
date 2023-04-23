package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

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

    @Lob
    @Column(name = "data", columnDefinition="BLOB")
    private byte[] data;

    private int correctAnswerIdx;
}
