package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class StartRoundDto extends RequestDto {
    @NonNull
    private SongEntryDto song;
    /**
     * Round length in milliseconds
     */
    private long length;
}
