package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.guess_the_song.server.entity.User;
import ru.guess_the_song.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManagerFactory entityManagerFactory;

    public UserRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteAll(Iterable<User> entities) {

    }

    @Override
    public void deleteAllById(Iterable<User> ids) {

    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<User> ids) {
        return null;
    }

    @Override
    public Optional<User> findById(UUID id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User result = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.uuid = :userUUID").setParameter("userUUID", id).getSingleResult(); // FIXME: 12.04.2023
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

        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }
}
