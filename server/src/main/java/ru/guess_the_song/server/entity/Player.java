package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
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
@Table(name = "PlayerTable")
@EqualsAndHashCode(callSuper = true)
public class Player extends BaseEntity {
    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    private int points;

    @Builder
    private Player(UUID id, User user, int points) {
        super(id);
        this.user = user;
        this.points = points;
    }
}
