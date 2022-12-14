package ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.StationExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.StationWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.StationRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.RemoteCrudRepository;

public class StationRepositoryImpl extends RemoteCrudRepository<Station, Long> implements StationRepository {
    public StationRepositoryImpl(StationWrapper stationWrapper, StationExtractor stationExtractor,
                                 ConnectionProvider connectionProvider) {
        super(stationWrapper, stationExtractor, connectionProvider);
    }

    @Override
    protected String table() {
        return "station";
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
