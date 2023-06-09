package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public UserRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public Optional<User> findById(UUID id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> result = entityManager
                .createQuery("FROM User u WHERE u.id = :userID")
                .setParameter("userID", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public <S extends User> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (entity.getId() != null) {
            Optional<User> optionalUser = findById(entity.getId());
            if (optionalUser.isPresent())
                entityManager.merge(entity);
            else
                entityManager.persist(entity);
        } else
            entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }
}
