package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.model.identifiable.SubjectName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";

    @Override
    public Subject map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        SubjectName name = findSubjectName(resultSet);
        return new Subject(id, name);
    }

    private SubjectName findSubjectName(ResultSet resultSet) throws SQLException {
        String nameValue = resultSet.getString(NAME_COLUMN);
        String nameUpperCase = nameValue.toUpperCase();
        return SubjectName.valueOf(nameUpperCase);
    }

}