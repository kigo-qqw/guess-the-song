package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.UUID;

@Getter
@SuperBuilder
public class GetMusicPackResponseDto extends ResponseDto {
    private UUID id;
    private @NonNull SongEntryDto[] songs;

    public String toString() {
        return "GetMusicPackResponseDto(super=" + super.toString() + ", id=" + this.getId() + ", songs=" + Arrays.toString(this.getSongs()) + ")";
    }
}