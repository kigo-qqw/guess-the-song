package ru.guess_the_song.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.guess_the_song.server.entity.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User extends BaseEntity {
    private String username;
}
