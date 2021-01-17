package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.FacultyDto;
import com.epam.university.model.identifiable.FacultyName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDtoRowMapper implements RowMapper<FacultyDto> {

    private final static String ID_COLUMN = "id";
    private final static String NAME_COLUMN = "name";
    private final static String RECRUITMENT_COLUMN = "recruitment";

    @Override
    public FacultyDto map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        FacultyName name = findFacultyName(resultSet);
        int recruitment = resultSet.getInt(RECRUITMENT_COLUMN);
        return new FacultyDto(id, name, recruitment);
    }

    private FacultyName findFacultyName(ResultSet resultSet) throws SQLException {
        String nameValue = resultSet.getString(NAME_COLUMN);
        String nameUpperCase = nameValue.toUpperCase();
        return FacultyName.valueOf(nameUpperCase);
    }

}