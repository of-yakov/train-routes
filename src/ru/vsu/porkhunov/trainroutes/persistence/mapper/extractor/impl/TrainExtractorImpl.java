package ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.impl;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.TrainExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainExtractorImpl implements TrainExtractor {
    @Override
    public List<Train> extractAll(ResultSet resultSet) throws SQLException {
        List<Train> trains = new ArrayList<>();

        while (resultSet.next()) {
            Train train = new Train();
            train.setId(resultSet.getLong(1));
            train.setName(resultSet.getString(2));

            trains.add(train);
        }

        return trains;
    }
}
