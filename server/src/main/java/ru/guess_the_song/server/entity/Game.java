package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.List;


@Getter
@Setter
@Entity
public class Game extends BaseEntity {
    @OneToMany
    private List<Player> users;
    @OneToOne
    private MusicPack musicPack;
}
