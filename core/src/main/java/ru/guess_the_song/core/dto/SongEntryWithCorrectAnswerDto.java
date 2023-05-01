package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

@Getter
@Builder
public class SongEntryWithCorrectAnswerDto extends EntityDto {
    private byte @NonNull [] data;
    private @NonNull String @NonNull [] answers;
    private int correctAnswerIdx;

    public String toString() {
        return "SongEntryWithCorrectAnswerDto(answers=" + Arrays.toString(this.getAnswers()) + ", correctAnswerIdx=" + this.getCorrectAnswerIdx() + ")";
    }
}
