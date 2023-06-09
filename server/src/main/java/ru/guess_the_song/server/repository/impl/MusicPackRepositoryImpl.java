package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.entity.Player;
import ru.guess_the_song.server.repository.MusicPackRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class MusicPackRepositoryImpl implements MusicPackRepository {
    @PersistenceUnit(name = "ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public MusicPackRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }

    @Override
    public <S extends MusicPack> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (entity.getId() != null) {
            Optional<MusicPack> optionalMusicPack = findById(entity.getId());
            if (optionalMusicPack.isPresent()) {
                entityManager.merge(entity);
                entity.getSongs().forEach(entityManager::merge);
            } else {
                entityManager.persist(entity);
                entity.getSongs().forEach(entityManager::persist);
            }
        } else {
            entityManager.persist(entity);
            entity.getSongs().forEach(entityManager::persist);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    @Override
    public Optional<MusicPack> findById(UUID id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<MusicPack> result = entityManager
                .createQuery("FROM MusicPack mp WHERE mp.id = :id")
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }
}
