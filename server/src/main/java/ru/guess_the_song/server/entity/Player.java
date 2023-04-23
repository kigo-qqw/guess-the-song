package ru.guess_the_song.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import ru.guess_the_song.server.entity.base.BaseEntity;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Players")
public class Player extends BaseEntity {
    @ManyToOne
    private User user;
}
