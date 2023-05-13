package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@EqualsAndHashCode(callSuper = true)
public class Player extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private int points;
    @Builder
    private Player(UUID id, User user) {
        super(id);
        this.user = user;
    }
}
