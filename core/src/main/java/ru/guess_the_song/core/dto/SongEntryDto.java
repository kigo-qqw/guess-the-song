package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class SongEntryDto implements Serializable {
    private SongDto song;
    private List<String> answers;
}
