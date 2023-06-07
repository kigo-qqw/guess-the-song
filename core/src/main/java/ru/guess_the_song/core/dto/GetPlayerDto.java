package ru.guess_the_song.core.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@ToString(callSuper = true)
public class GetPlayerDto extends RequestDto {
    @NonNull
    private UUID id;
}
