package com.epam.university.mapper;

import com.epam.university.model.SubjectName;
import com.epam.university.model.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {

    @Override
    public Subject map(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");

        String nameValue = resultSet.getString("name");
        String nameUpperCase = nameValue.toUpperCase();
        SubjectName name = SubjectName.valueOf(nameUpperCase);

        return new Subject(id, name);
    }

}