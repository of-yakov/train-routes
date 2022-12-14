package ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.TrainExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.TrainWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.TrainRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.RemoteCrudRepository;

public class TrainRepositoryImpl extends RemoteCrudRepository<Train, Long> implements TrainRepository {
    public TrainRepositoryImpl(TrainWrapper trainWrapper, TrainExtractor trainExtractor,
                               ConnectionProvider connectionProvider) {
        super(trainWrapper, trainExtractor, connectionProvider);
    }

    @Override
    protected String table() {
        return "train";
    }

    @Override
    protected String id() {
        return "id";
    }

    @Override
    protected String[] columns() {
        return new String[]{"name"};
    }
}
