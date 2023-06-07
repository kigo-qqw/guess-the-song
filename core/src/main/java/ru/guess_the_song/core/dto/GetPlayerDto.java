package ru.guess_the_song.core.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@ToString
public class GetPlayerDto extends RequestDto {
    @NonNull
    private UUID id;
}
