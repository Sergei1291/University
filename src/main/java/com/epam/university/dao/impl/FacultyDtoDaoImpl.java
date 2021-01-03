package com.epam.university.dao.impl;

import com.epam.university.dao.AbstractDao;
import com.epam.university.dao.FacultyDtoDao;
import com.epam.university.mapper.impl.FacultyDtoRowMapper;
import com.epam.university.model.FacultyDto;

import java.sql.Connection;

public class FacultyDtoDaoImpl extends AbstractDao<FacultyDto> implements FacultyDtoDao {

    private static final String TABLE_NAME = "faculty";

    public FacultyDtoDaoImpl(Connection connection) {

        super(connection, new FacultyDtoRowMapper(), TABLE_NAME);

    }

}