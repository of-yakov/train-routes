package ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.StationExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationExtractorImpl implements StationExtractor {
    @Override
    public List<Station> extractAll(ResultSet resultSet) throws SQLException {
        List<Station> stations = new ArrayList<>();

        while (resultSet.next()) {
            Station station = new Station();
            station.setId(resultSet.getLong(1));
            station.setName(resultSet.getString(2));

            stations.add(station);
        }

        return stations;
    }
}
