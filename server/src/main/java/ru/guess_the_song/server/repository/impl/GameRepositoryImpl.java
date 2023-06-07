package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.Game;
import ru.guess_the_song.server.repository.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class GameRepositoryImpl implements GameRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public GameRepositoryImpl() {
        log.debug("GameRepositoryImpl created");
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public Optional<Game> findById(UUID id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Game result = (Game) entityManager
                .createQuery("FROM Game g WHERE g.id = :gameID")
                .setParameter("gameID", id)
                .getResultList().get(0);
//                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return Optional.of(result);
    }

    @Override
    public List<Game> findAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Game> result = entityManager.createQuery("FROM Game g").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public List<Game> findAllActive() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Game> result = entityManager.createQuery(
                "FROM Game g WHERE " +
                        "g.isCanceled=false and " +
                        "g.isStarted=false and " +
                        "g.isFinished=false"
        ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public <S extends Game> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        log.debug("GAME ID=" + entity.getId());
        if (entity.getId() != null)
            entityManager.merge(entity);
        else
            entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }
}
