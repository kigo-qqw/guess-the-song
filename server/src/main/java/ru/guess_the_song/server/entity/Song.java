package ru.guess_the_song.server.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
public class Song extends Entity {
    @Getter
    @Setter
    UUID uuid;
}
