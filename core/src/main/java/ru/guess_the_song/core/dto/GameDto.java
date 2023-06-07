package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class GameDto extends EntityDto {
    @NonNull
    private UUID id;
    @NonNull
    private UUID leaderId;
    private @NonNull PlayerDto @NonNull [] players;

    public String toString() {
        return "GameDto(super=" + super.toString() + ", id=" + this.getId() + ", leaderId=" + this.getLeaderId() + ", players=" + java.util.Arrays.toString(this.getPlayers()) + ")";
    }
}