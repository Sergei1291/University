package com.epam.university.mapper;

import com.epam.university.model.FacultyDto;
import com.epam.university.model.FacultyName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDtoRowMapper implements RowMapper<FacultyDto> {

    @Override
    public FacultyDto map(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");

        String nameValue = resultSet.getString("name");
        String nameUpperCase = nameValue.toUpperCase();
        FacultyName name = FacultyName.valueOf(nameUpperCase);

        int recruitment = resultSet.getInt("recruitment");

        return new FacultyDto(id, name, recruitment);
    }

}