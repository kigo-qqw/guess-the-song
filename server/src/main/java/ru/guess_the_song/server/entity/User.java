package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User extends BaseEntity {
    private String username;

    @Builder
    private User(UUID id, String username) {
        super(id);
        this.username = username;
    }
}
