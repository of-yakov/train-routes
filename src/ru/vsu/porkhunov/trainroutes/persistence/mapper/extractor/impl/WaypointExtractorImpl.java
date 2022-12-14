package ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.WaypointExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WaypointExtractorImpl implements WaypointExtractor {
    @Override
    public List<Waypoint> extractAll(ResultSet resultSet) throws SQLException {
        List<Waypoint> waypoints = new ArrayList<>();

        while (resultSet.next()) {
            Waypoint waypoint = new Waypoint();
            waypoint.setId(resultSet.getLong(1));
            waypoint.setRouteId(resultSet.getLong(2));
            waypoint.setStationId(resultSet.getLong(3));

            Timestamp departsAt = resultSet.getTimestamp(4);

            if (departsAt != null) {
                waypoint.setDepartsAt(departsAt.toLocalDateTime());
            }

            Timestamp arrivesAt = resultSet.getTimestamp(5);

            if (arrivesAt != null) {
                waypoint.setArrivesAt(arrivesAt.toLocalDateTime());
            }

            waypoints.add(waypoint);
        }

        return waypoints;
    }
}
