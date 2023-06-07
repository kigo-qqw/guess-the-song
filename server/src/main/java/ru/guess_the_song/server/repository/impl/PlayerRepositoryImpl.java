package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.PlayerRepository;

import java.util.Optional;

@Slf4j
@Component
public class PlayerRepositoryImpl implements PlayerRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public PlayerRepositoryImpl() {
        log.debug("PlayerRepositoryImpl created");
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public <S extends Player> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.persist(entity);

        if (entity.getId() != null)
            entityManager.merge(entity);
        else
            entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    @Override
    public Optional<Player> findByUser(User user) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Player result = (Player) entityManager
                .createQuery("FROM Player p WHERE p.user = :user")
                .setParameter("user", user)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return Optional.of(result);
    }
}
