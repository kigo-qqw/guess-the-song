package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "UserTable")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String username;

    @Builder
    private User(UUID id, String username) {
        super(id);
        this.username = username;
    }
}
