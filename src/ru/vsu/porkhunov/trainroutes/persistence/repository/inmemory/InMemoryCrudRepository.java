package ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory;

import ru.vsu.porkhunov.trainroutes.entity.Entity;
import ru.vsu.porkhunov.trainroutes.persistence.provider.id.IdProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.CrudRepository;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;

import java.util.*;

public abstract class InMemoryCrudRepository<E extends Entity<Id>, Id> implements CrudRepository<E, Id> {
    protected final IdProvider<Id> idProvider;
    protected final Set<E> storage;

    public InMemoryCrudRepository(IdProvider<Id> idProvider) {
        this(idProvider, null);
    }

    public InMemoryCrudRepository(IdProvider<Id> idProvider, RepositoryInitializer<E> initializer) {
        this.idProvider = idProvider;
        storage = new HashSet<>();
        initialize(initializer);
    }

    @Override
    public void deleteById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        storage.removeIf(entity -> Objects.equals(entity.getId(), id));
    }

    @Override
    public boolean existsById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return storage.stream()
                .anyMatch(entity -> Objects.equals(entity.getId(), id));
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public Optional<E> findById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return storage.stream()
                .filter(entity -> Objects.equals(entity.getId(), id))
                .findFirst();
    }

    @Override
    public E save(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        if (storage.contains(entity)) {
            storage.remove(entity);
        } else {
            entity.setId(idProvider.provide());
        }

        storage.add(entity);

        return entity;
    }

    private void initialize(RepositoryInitializer<E> initializer) {
        if (initializer != null) {
            initializer.values()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(entity -> {
                        entity.setId(null);

                        save(entity);
                    });
        }
    }
}
