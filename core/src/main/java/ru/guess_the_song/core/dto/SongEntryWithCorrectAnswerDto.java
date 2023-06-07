package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;

@Getter
@SuperBuilder
public class SongEntryWithCorrectAnswerDto extends EntityDto {
    private byte @NonNull [] data;
    private @NonNull String @NonNull [] answers;
    private int correctAnswerIdx;

    public String toString() {
        return "SongEntryWithCorrectAnswerDto(super=" + super.toString() + ", data=" + Arrays.toString(this.getData()) + ", answers=" + Arrays.toString(this.getAnswers()) + ", correctAnswerIdx=" + this.getCorrectAnswerIdx() + ")";
    }
}
