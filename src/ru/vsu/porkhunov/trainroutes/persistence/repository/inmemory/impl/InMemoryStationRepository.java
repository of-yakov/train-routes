package ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.impl;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.persistence.provider.id.LongIdProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.StationRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.InMemoryCrudRepository;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;

public class InMemoryStationRepository extends InMemoryCrudRepository<Station, Long> implements StationRepository {
    public InMemoryStationRepository(LongIdProvider idProvider) {
        super(idProvider);
    }

    public InMemoryStationRepository(LongIdProvider idProvider, RepositoryInitializer<Station> initializer) {
        super(idProvider, initializer);
    }
}
