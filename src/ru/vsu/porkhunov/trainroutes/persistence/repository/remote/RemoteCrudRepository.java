package ru.vsu.porkhunov.trainroutes.persistence.repository.remote;

import ru.vsu.porkhunov.trainroutes.entity.Entity;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.extractor.Extractor;
import ru.vsu.porkhunov.trainroutes.persistence.mapper.wrapper.Wrapper;
import ru.vsu.porkhunov.trainroutes.persistence.provider.connection.ConnectionProvider;
import ru.vsu.porkhunov.trainroutes.persistence.repository.CrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class RemoteCrudRepository<E extends Entity<Id>, Id> implements CrudRepository<E, Id> {
    protected final Wrapper<E> wrapper;
    protected final Extractor<E> extractor;
    protected final ConnectionProvider connectionProvider;

    public RemoteCrudRepository(Wrapper<E> wrapper, Extractor<E> extractor, ConnectionProvider connectionProvider) {
        this.wrapper = wrapper;
        this.extractor = extractor;
        this.connectionProvider = connectionProvider;
    }

    @Override
    public final void deleteById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        try (Connection connection = connectionProvider.provide()) {
            String sql = deleteByIdQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows < 1) {
                throw new SQLException("Error occurred while deleting entity. No rows affected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public final boolean existsById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        try (Connection connection = connectionProvider.provide()) {
            String sql = existsByIdQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new SQLException("Error occurred while extracting query results");
            }

            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public final List<E> findAll() {
        try (Connection connection = connectionProvider.provide()) {
            String sql = selectAllQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public final Optional<E> findById(Id id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        try (Connection connection = connectionProvider.provide()) {
            String sql = selectByIdQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return extractor.extractFirst(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public final E save(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        return entity.getId() == null ? insert(entity) : update(entity);
    }

    @SuppressWarnings(value = "unchecked")
    private E insert(E entity) {
        try (Connection connection = connectionProvider.provide()) {
            String sql = insertQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            wrapper.wrap(entity, preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new SQLException("Error occurred while inserting entity. " +
                        "Impossible to get id of created entity");
            }

            entity.setId((Id) resultSet.getObject(1));

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private E update(E entity) {
        try (Connection connection = connectionProvider.provide()) {
            String sql = updateQuery();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(columns().length + 1, entity.getId());
            wrapper.wrap(entity, preparedStatement);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows < 1) {
                throw new SQLException("Error occurred while updating entity. No rows affected");
            }

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected abstract String table();

    protected abstract String id();

    protected abstract String[] columns();

    private String deleteByIdQuery() {
        return String.format("delete from %s where %s=?", table(), id());
    }

    private String existsByIdQuery() {
        return String.format("select exists(select 1 from %s where %s=?)", table(), id());
    }

    private String selectAllQuery() {
        return String.format("select * from %s", table());
    }

    private String selectByIdQuery() {
        return String.format("select * from %s where %s=?", table(), id());
    }

    private String insertQuery() {
        String columns = String.join(", ", columns());
        String values = String.join(", ", "? ".repeat(columns().length).split(" "));

        return String.format("insert into %s (%s) values (%s) returning %s", table(), columns, values, id());
    }

    private String updateQuery() {
        String columns = Arrays.stream(columns())
                .map(column -> String.format("%s=?", column))
                .collect(Collectors.joining(", "));

        return String.format("update %s set %s where %s=?", table(), columns, id());
    }
}
