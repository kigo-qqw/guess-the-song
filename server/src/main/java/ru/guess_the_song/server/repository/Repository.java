package ru.guess_the_song.server.repository;

import ru.guess_the_song.server.entity.EntityEE;

import java.util.Optional;

public interface Repository<T extends EntityEE, ID> {
    long count();
    void delete(T entity);
    void deleteAll();
    void deleteAll(Iterable<T> entities);
    void deleteAllById(Iterable<T> ids);
    void deleteById(ID id);
    boolean existsById(ID id);
    Iterable<T> findAll();
    Iterable<T> findAllById(Iterable<T>ids);
    Optional<T> findById(ID id);
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
}
