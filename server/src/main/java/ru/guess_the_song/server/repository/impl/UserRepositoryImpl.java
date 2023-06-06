package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public UserRepositoryImpl() {
        log.debug("UserRepositoryImpl created");
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.debug("find user : " + id);
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User result = (User) entityManager
                .createQuery("FROM User u WHERE u.id = :userID")
                .setParameter("userID", id)
                .getResultList().get(0);
//                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return Optional.of(result);
    }

    @Override
    public <S extends User> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

        log.debug("save user : " + entity);
        return entity;
    }
}
