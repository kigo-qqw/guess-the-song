package ru.guess_the_song.core.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.UUID;

@Getter
@Builder
public class MusicPackWithCorrectAnswersDto extends EntityDto {
    private UUID id;

    private @NonNull SongEntryWithCorrectAnswerDto @NonNull [] songs;

    public String toString() {
        return "MusicPackWithCorrectAnswersDto(id=" + this.getId() + ", songs=" + Arrays.toString(this.getSongs()) + ")";
    }
}