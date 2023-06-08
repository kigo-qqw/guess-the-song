package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;

@Getter
@SuperBuilder
public class SongEntryDto extends EntityDto {
    private byte @NonNull [] data;
    private @NonNull String @NonNull [] answers;

    public String toString() {
        return "SongEntryDto(super=" + super.toString() + ", data=[...], answers=" + Arrays.toString(this.getAnswers()) + ")";
    }
}
