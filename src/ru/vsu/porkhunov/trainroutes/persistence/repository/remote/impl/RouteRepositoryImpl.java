package ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.RouteExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.RouteWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.RouteRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.RemoteCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteRepositoryImpl extends RemoteCrudRepository<Route, Long> implements RouteRepository {
    public RouteRepositoryImpl(RouteWrapper routeWrapper, RouteExtractor routeExtractor,
                               ConnectionProvider connectionProvider) {
        super(routeWrapper, routeExtractor, connectionProvider);
    }

    @Override
    public List<Route> findAllByTrainId(Long trainId) {
        if (trainId == null) {
            throw new IllegalArgumentException("Train id cannot be null");
        }

        try (Connection connection = connectionProvider.provide()) {
            String sql = "select * from route where train_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    protected String table() {
        return "route";
    }

    @Override
    protected String id() {
        return "id";
    }

    @Override
    protected String[] columns() {
        return new String[]{"name", "train_id", "departure_station_id", "arrival_station_id"};
    }
}
