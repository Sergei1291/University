package com.epam.university.mapper.impl;

import com.epam.university.mapper.RowMapper;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.FacultyName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDtoRowMapper implements RowMapper<FacultyDto> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";
    private final static String RECRUITMENT_COLUMN = "recruitment";

    @Override
    public FacultyDto map(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(ID_COLUMN);

        String nameValue = resultSet.getString(NAME_COLUMN);
        String nameUpperCase = nameValue.toUpperCase();
        FacultyName name = FacultyName.valueOf(nameUpperCase);

        int recruitment = resultSet.getInt(RECRUITMENT_COLUMN);

        return new FacultyDto(id, name, recruitment);
    }

}