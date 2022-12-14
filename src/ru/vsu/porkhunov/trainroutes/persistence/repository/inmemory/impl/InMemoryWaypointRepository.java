package ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.impl;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.provider.id.LongIdProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.WaypointRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.InMemoryCrudRepository;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryWaypointRepository extends InMemoryCrudRepository<Waypoint, Long> implements WaypointRepository {
    public InMemoryWaypointRepository(LongIdProvider idProvider) {
        super(idProvider);
    }

    public InMemoryWaypointRepository(LongIdProvider idProvider, RepositoryInitializer<Waypoint> initializer) {
        super(idProvider, initializer);
    }

    @Override
    public List<Waypoint> findAllByRouteIdAndSortedByArrivesAt(Long routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("Route id cannot be null");
        }

        return storage.stream()
                .filter(station -> Objects.equals(station.getRouteId(), routeId))
                .sorted(Comparator.comparing(Waypoint::getArrivesAt))
                .collect(Collectors.toList());
    }
}
