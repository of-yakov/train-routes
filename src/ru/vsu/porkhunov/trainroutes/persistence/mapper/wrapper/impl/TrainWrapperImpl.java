package ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.impl;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.TrainWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainWrapperImpl implements TrainWrapper {
    @Override
    public void wrap(Train train, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, train.getName());
    }
}
