package ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.impl;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.persistence.provider.id.LongIdProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.RouteRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.InMemoryCrudRepository;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryRouteRepository extends InMemoryCrudRepository<Route, Long> implements RouteRepository {
    public InMemoryRouteRepository(LongIdProvider idProvider) {
        super(idProvider);
    }

    public InMemoryRouteRepository(LongIdProvider idProvider, RepositoryInitializer<Route> initializer) {
        super(idProvider, initializer);
    }

    @Override
    public List<Route> findAllByTrainId(Long trainId) {
        if (trainId == null) {
            throw new IllegalArgumentException("Train id cannot be null");
        }

        return storage.stream()
                .filter(route -> Objects.equals(route.getTrainId(), trainId))
                .collect(Collectors.toList());
    }
}
