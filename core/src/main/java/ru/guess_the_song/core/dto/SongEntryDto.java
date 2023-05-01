package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

@Getter
@Builder
public class SongEntryDto extends EntityDto {
    private byte @NonNull [] data;
    private @NonNull String @NonNull [] answers;

    public String toString() {
        return "SongEntryDto(answers=" + Arrays.toString(this.getAnswers()) + ")";
    }
}
