package ru.guess_the_song.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
//@Builder
@NoArgsConstructor
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
    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;

    private int correctAnswerIdx;

    @Builder
    private SongEntry(UUID id, byte[] data, int correctAnswerIdx) {
        super(id);
        this.data = data;
        this.correctAnswerIdx = correctAnswerIdx;
    }
}
