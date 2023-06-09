package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "PlayerTable")
@EqualsAndHashCode(callSuper = true)
public class Player extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    private int points;
    private boolean isInGame;

    @Builder
    private Player(UUID id, User user, Game game, int points, boolean isInGame) {
        super(id);
        this.user = user;
        this.game = game;
        this.points = points;
        this.isInGame = isInGame;
    }
}
