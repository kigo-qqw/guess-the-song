package ru.guess_the_song.server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.guess_the_song.server.entity.MusicPack;
import ru.guess_the_song.server.repository.MusicPackRepository;

@Slf4j
@Component
public class MusicPackRepositoryImpl implements MusicPackRepository {
    @PersistenceUnit(name="ru.guess_the_song.server")
    private final EntityManagerFactory entityManagerFactory;

    public MusicPackRepositoryImpl() {
        log.debug("MusicPackRepositoryImpl created");
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ru.guess_the_song.server");
    }
    @Override
    public <S extends MusicPack> S save(S entity) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entity.getSongs().forEach(entityManager::persist);
//        entityManager.persist(entity);

        if (entity.getId() != null)
            entityManager.merge(entity);
        else
            entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }
//    @Override
//    public Optional<MusicPack> findById(UUID uuid) {
//        return Optional.of(
//                MusicPack.builder()
//                        .uuid(uuid)
//                        .songs(
//                                List.of(
//                                        SongEntry.builder()
//                                                .uuid(UUID.randomUUID())
//                                                .song(Song.builder().uuid(UUID.randomUUID()).build())
//                                                .answers(List.of(
//                                                        "[1] - [1]",
//                                                        "[1] - [2]",
//                                                        "[1] - [3]",
//                                                        "[1] - [4]")
//                                                )
//                                                .correctAnswerIdx(0)
//                                                .build(),
//                                        SongEntry.builder()
//                                                .uuid(UUID.randomUUID())
//                                                .song(Song.builder().uuid(UUID.randomUUID()).build())
//                                                .answers(List.of(
//                                                        "[2] - [1]",
//                                                        "[2] - [2]",
//                                                        "[2] - [3]",
//                                                        "[2] - [4]")
//                                                )
//                                                .correctAnswerIdx(0)
//                                                .build()
//                                )
//                        ).build()
//        );
//        // return Optional.empty(); // FIXME: 10.04.2023
//    }
}
