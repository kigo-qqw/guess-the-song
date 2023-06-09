package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PlayerRepositoryImpl implements PlayerRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public PlayerRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public <S extends Player> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (entity.getId() != null) {
            Optional<Player> optionalPlayer = findById(entity.getId());
            if (optionalPlayer.isPresent())
                entityManager.merge(entity);
            else
                entityManager.persist(entity);
        } else
            entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    @Override
    public Optional<Player> findByGameAndUser(Game game, User user) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Player> result = entityManager
                .createQuery("FROM Player p WHERE p.user = :user AND p.game = :game")
                .setParameter("user", user)
                .setParameter("game", game)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public Optional<Player> findById(UUID id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Player> result = entityManager
                .createQuery("FROM Player p WHERE p.id = :playerID")
                .setParameter("playerID", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }
}
