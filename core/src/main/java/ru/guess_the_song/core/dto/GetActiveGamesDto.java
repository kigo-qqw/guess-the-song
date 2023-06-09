package ru.guess_the_song.core.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class GetActiveGamesDto extends RequestDto {
}
