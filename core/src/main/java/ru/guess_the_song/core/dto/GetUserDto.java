package ru.guess_the_song.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class GetUserDto extends RequestDto {
    @NonNull
    private UUID userId;
}
