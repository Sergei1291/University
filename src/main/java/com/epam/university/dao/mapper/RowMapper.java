package com.epam.university.dao.mapper;

import com.epam.university.model.identifiable.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    /**
     * This method is used to create object type T from row of ResultSet object.
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    T map(ResultSet resultSet) throws SQLException;

}