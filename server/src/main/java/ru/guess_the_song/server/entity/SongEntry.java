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
@ToString
@Entity
//@Table(name = "SONG_ENTRY")
public class SongEntry extends BaseEntity {
    @Lob
    @ToString.Exclude
//    @Transient
    private byte[] data;

    @ElementCollection // 1
    private List<String> answers;

    private int correctAnswerIdx;

    @Builder
    private SongEntry(UUID id, byte[] data, List<String> answers, int correctAnswerIdx) {
        super(id);
        this.data = data;
        this.answers = answers;
        this.correctAnswerIdx = correctAnswerIdx;
    }
}
