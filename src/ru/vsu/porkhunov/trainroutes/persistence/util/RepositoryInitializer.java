package ru.vsu.porkhunov.trainroutes.persistence.util;

import ru.vsu.porkhunov.trainroutes.entity.Entity;

import java.util.List;

public interface RepositoryInitializer<E extends Entity<?>> {
    List<E> values();
}
