package ru.vsu.porkhunov.trainroutes.service;

import ru.vsu.porkhunov.trainroutes.entity.Entity;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.List;

public interface Service<E extends Entity<Id>, Id> {
    E add(E entity);

    void deleteById(Id id) throws EntityNotFoundException;

    boolean existsById(Id id);

    List<E> findAll();

    E findById(Id id) throws EntityNotFoundException;

    E updateById(E entity, Id id) throws EntityNotFoundException;
}
