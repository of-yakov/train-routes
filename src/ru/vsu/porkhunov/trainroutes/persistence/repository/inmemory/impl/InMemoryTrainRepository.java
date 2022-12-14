package ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.impl;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.persistence.provider.id.LongIdProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.TrainRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.inmemory.InMemoryCrudRepository;
import ru.vsu.porkhunov.trainroutes.persistence.util.RepositoryInitializer;

public class InMemoryTrainRepository extends InMemoryCrudRepository<Train, Long> implements TrainRepository {
    public InMemoryTrainRepository(LongIdProvider idProvider) {
        super(idProvider);
    }

    public InMemoryTrainRepository(LongIdProvider idProvider, RepositoryInitializer<Train> initializer) {
        super(idProvider, initializer);
    }
}
