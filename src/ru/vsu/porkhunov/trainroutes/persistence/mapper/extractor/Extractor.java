package ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor;

import ru.vsu.porkhunov.trainroutes.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Extractor<E extends Entity<?>> {
    List<E> extractAll(ResultSet resultSet) throws SQLException;

    default Optional<E> extractFirst(ResultSet resultSet) throws SQLException {
        List<E> extracted = extractAll(resultSet);

        return !extracted.isEmpty() ? Optional.of(extracted.get(0)) : Optional.empty();
    }
}
