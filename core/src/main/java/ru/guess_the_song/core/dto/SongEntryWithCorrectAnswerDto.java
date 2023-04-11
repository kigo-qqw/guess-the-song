package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SongEntryWithCorrectAnswerDto extends EntityDto {
    private SongDto song;
    private List<String> answers;
    private int correctAnswerIdx;
}
