package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class SongEntryDto extends EntityDto {
    private SongDto song;
    private List<String> answers;
}
