package com.epam.university.dao;

import com.epam.university.mapper.RowMapper;
import com.epam.university.model.Identifiable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final Connection connection;
    private final RowMapper<T> rowMapper;
    private final String tableName;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper, String tableName) {

        this.connection = connection;
        this.rowMapper = rowMapper;
        this.tableName = tableName;

    }

    @Override
    public List<T> getAll() throws DaoException {

        return executeQuery(String.format("select * from %s;", tableName));
    }

    @Override
    public Optional<T> findById(Integer id) throws DaoException {

        String query = String.format("select * from %s where id = ?;", tableName);

        return executeForSingleResult(query, id);
    }

    @Override
    public void save(T t) throws DaoException {

    }

    @Override
    public void removeById(Integer id) throws DaoException {

        String query = String.format("delete from %s where id = ?;", tableName);

        executeUpdate(query, id);

    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {

            List<T> identifiableList = new ArrayList<>();
            while (resultSet.next()) {
                T identifiable = rowMapper.map(resultSet);
                identifiableList.add(identifiable);
            }

            return identifiableList;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    protected int executeUpdate(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {

        List<T> identifiableList = executeQuery(query, params);

        if (identifiableList.size() == 0) {
            return Optional.empty();
        } else if (identifiableList.size() == 1) {
            return Optional.of(identifiableList.get(0));
        }

        throw new DaoException("illegal arguments for single result: results are more than 1");

    }

    protected int executeQueryCounter(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {

            int count = 0;

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }

            return count;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }

        return statement;
    }

}