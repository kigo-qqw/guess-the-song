package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;


@Getter
@Setter
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@ToString
//@Table(name = "Players")
public class Player extends BaseEntity {
    @ManyToOne
    private User user;

    @Builder
    private Player(UUID id, User user) {
        super(id);
        this.user = user;
    }
}
