package ru.guess_the_song.core.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.UUID;

@Getter
@Builder
public class MusicPackDto extends EntityDto {
    @NonNull
    private UUID uuid;

    private @NonNull SongEntryDto @NonNull [] songs;

    public String toString() {
        return "MusicPackDto(super=" + super.toString() + ", uuid=" + this.getUuid() + ", songs=" + Arrays.toString(this.getSongs()) + ")";
    }
}
