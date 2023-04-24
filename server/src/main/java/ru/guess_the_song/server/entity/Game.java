package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game extends BaseEntity {
    @OneToOne
    private Player leader;
    @OneToMany
    private List<Player> players;
    @OneToOne
    private MusicPack musicPack;

    @Builder
    private Game(UUID id, Player leader, List<Player> players, MusicPack musicPack) {
        super(id);
        this.leader = leader;
        this.players = players;
        this.musicPack = musicPack;
    }
}
