package ru.guess_the_song.server.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
public class SongEntry extends EntityEE {
    @Getter
    @Setter
    private UUID uuid;
    @Getter
    @Setter
    private Song song;
    @Getter
    @Setter
    private List<String> answers;
    @Getter
    @Setter
    private int correctAnswerIdx;
}
