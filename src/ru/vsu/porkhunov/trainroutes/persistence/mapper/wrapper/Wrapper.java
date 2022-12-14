package ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper;

import ru.vsu.porkhunov.trainroutes.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Wrapper<E extends Entity<?>> {
    void wrap(E entity, PreparedStatement preparedStatement) throws SQLException;
}
