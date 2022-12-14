package ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.RouteExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteExtractorImpl implements RouteExtractor {
    @Override
    public List<Route> extractAll(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();

        while (resultSet.next()) {
            Route route = new Route();
            route.setId(resultSet.getLong(1));
            route.setName(resultSet.getString(2));
            route.setTrainId(resultSet.getLong(3));
            route.setDepartureStationId(resultSet.getLong(4));
            route.setArrivalStationId(resultSet.getLong(5));

            routes.add(route);
        }

        return routes;
    }
}
