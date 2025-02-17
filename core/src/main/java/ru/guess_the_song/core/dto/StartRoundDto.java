package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class StartRoundDto extends ServerMessageDto {
    @NonNull
    private UUID gameId;
    @NonNull
    private SongEntryDto song;
    /**
     * Round length in milliseconds
     */
    private long length;
}
