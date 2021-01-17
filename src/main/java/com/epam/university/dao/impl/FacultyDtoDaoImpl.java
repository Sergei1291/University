package com.epam.university.dao.impl;

import com.epam.university.dao.api.AbstractDao;
import com.epam.university.dao.api.FacultyDtoDao;
import com.epam.university.dao.mapper.impl.FacultyDtoRowMapper;
import com.epam.university.model.identifiable.FacultyDto;

import java.sql.Connection;

public class FacultyDtoDaoImpl extends AbstractDao<FacultyDto> implements FacultyDtoDao {

    private static final String TABLE_NAME = "faculty";

    public FacultyDtoDaoImpl(Connection connection) {
        super(connection, new FacultyDtoRowMapper(), TABLE_NAME);
    }

}