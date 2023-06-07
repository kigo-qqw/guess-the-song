package ru.guess_the_song.core.dto;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@ToString(callSuper = true)
public abstract class EntityDto implements Serializable {
}
