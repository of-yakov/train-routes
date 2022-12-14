package ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.WaypointWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class WaypointWrapperImpl implements WaypointWrapper {
    @Override
    public void wrap(Waypoint waypoint, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, waypoint.getRouteId());
        preparedStatement.setLong(2, waypoint.getStationId());

        if (waypoint.getDepartsAt() != null) {
            preparedStatement.setTimestamp(3, Timestamp.valueOf(waypoint.getDepartsAt()));
        }

        if (waypoint.getArrivesAt() != null) {
            preparedStatement.setTimestamp(4, Timestamp.valueOf(waypoint.getArrivesAt()));
        }
    }
}
