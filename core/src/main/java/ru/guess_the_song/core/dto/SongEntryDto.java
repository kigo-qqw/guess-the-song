package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SongEntryDto extends EntityDto {
    @ToString.Exclude
    private byte @NonNull [] data;
    private @NonNull String @NonNull [] answers;
}
