package ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.StationWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StationWrapperImpl implements StationWrapper {
    @Override
    public void wrap(Station station, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, station.getName());
    }
}
