package ru.vsu.porkhunov.trainroutes.persistence.repository;

import ru.vsu.porkhunov.trainroutes.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends Entity<Id>, Id> {
    void deleteById(Id id);

    boolean existsById(Id id);

    List<E> findAll();

    Optional<E> findById(Id id);

    E save(E entity);
}
