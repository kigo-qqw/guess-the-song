package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
@Builder
public class GameDto extends EntityDto {
    @NonNull
    private UUID id;
    @NonNull
    private UUID leaderId;
    private @NonNull PlayerDto @NonNull [] players;

    public String toString() {
        return "GameDto(id=" + this.getId() + ", leaderId=" + this.getLeaderId() + ", players=" + java.util.Arrays.toString(this.getPlayers()) + ")";
    }
}