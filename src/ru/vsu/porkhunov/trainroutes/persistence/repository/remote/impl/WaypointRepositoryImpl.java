package ru.vsu.porkhunov.trainroutes.persistence.repository.remote.impl;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.WaypointExtractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.WaypointWrapper;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.WaypointRepository;
import ru.vsu.porkhunov.trainroutes.persistence.repository.remote.RemoteCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WaypointRepositoryImpl extends RemoteCrudRepository<Waypoint, Long> implements WaypointRepository {
    public WaypointRepositoryImpl(WaypointWrapper waypointWrapper, WaypointExtractor waypointExtractor,
                                  ConnectionProvider connectionProvider) {
        super(waypointWrapper, waypointExtractor, connectionProvider);
    }

    @Override
    public List<Waypoint> findAllByRouteIdAndSortedByArrivesAt(Long routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("Route id cannot be null");
        }

        try (Connection connection = connectionProvider.provide()) {
            String sql = "select * from waypoint where route_id=? order by departs_at";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, routeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    protected String table() {
        return "waypoint";
    }

    @Override
    protected String id() {
        return "id";
    }

    @Override
    protected String[] columns() {
        return new String[]{"route_id", "station_id", "departs_at", "arrives_at"};
    }
}
