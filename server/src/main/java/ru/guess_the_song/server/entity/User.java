package ru.guess_the_song.server.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class User extends EntityEE {
    private UUID uuid;
    private String username;
}
