package ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.RouteWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RouteWrapperImpl implements RouteWrapper {
    @Override
    public void wrap(Route route, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, route.getName());
        preparedStatement.setLong(2, route.getTrainId());
        preparedStatement.setLong(3, route.getDepartureStationId());
        preparedStatement.setLong(4, route.getArrivalStationId());
    }
}
