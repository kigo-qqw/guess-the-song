package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class EndRoundDto extends ServerMessageDto {
    @NonNull
    private UUID gameId;
    private @NonNull String @NonNull [] answers;
    private int correctAnswerId;
    @NonNull
    private PlayerDto @NonNull [] players;

    public String toString() {
        return "EndRoundDto(super=" + super.toString() + ", gameId=" + this.getGameId() + ", answers=" + java.util.Arrays.toString(this.getAnswers()) + ", correctAnswerId=" + this.getCorrectAnswerId() + ", players=" + java.util.Arrays.toString(this.getPlayers()) + ")";
    }
}