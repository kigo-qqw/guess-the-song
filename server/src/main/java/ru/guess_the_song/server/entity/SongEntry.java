package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "SongEntryTable")
@EqualsAndHashCode(callSuper = true)
public class SongEntry extends BaseEntity {
    @Lob
    @ToString.Exclude
    private byte[] data;

    @ElementCollection(fetch = FetchType.EAGER)
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
