package com.epam.university.mapper.impl;

import com.epam.university.mapper.RowMapper;
import com.epam.university.model.SubjectName;
import com.epam.university.model.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";

    @Override
    public Subject map(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(ID_COLUMN);

        String nameValue = resultSet.getString(NAME_COLUMN);
        String nameUpperCase = nameValue.toUpperCase();
        SubjectName name = SubjectName.valueOf(nameUpperCase);

        return new Subject(id, name);
    }

}