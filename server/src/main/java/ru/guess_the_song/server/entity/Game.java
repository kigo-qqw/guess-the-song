package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "GameTable")
@EqualsAndHashCode(callSuper = true)
public class Game extends BaseEntity {
    @OneToOne
    private Player leader;
    //    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Player> players;
    @OneToOne
    private MusicPack musicPack;

    @Builder
    private Game(UUID id, Player leader, List<Player> players, MusicPack musicPack) {
        super(id);
        this.leader = leader;
        this.players = Objects.requireNonNullElseGet(players, ArrayList::new);
        this.musicPack = musicPack;
    }
}
