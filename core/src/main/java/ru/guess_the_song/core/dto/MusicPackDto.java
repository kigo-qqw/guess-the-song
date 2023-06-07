package ru.guess_the_song.core.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.UUID;

@Getter
@SuperBuilder
public class MusicPackDto extends EntityDto {
    @NonNull
    private UUID id;

    private @NonNull SongEntryDto @NonNull [] songs;

    public String toString() {
        return "MusicPackDto(super=" + super.toString() + ", id=" + this.getId() + ", songs=" + Arrays.toString(this.getSongs()) + ")";
    }
}
