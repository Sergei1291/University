package com.epam.university.dao.impl;

import com.epam.university.dao.api.AbstractDao;
import com.epam.university.dao.api.FacultyDao;
import com.epam.university.dao.mapper.impl.FacultyRowMapper;
import com.epam.university.model.identifiable.Faculty;

import java.sql.Connection;

public class FacultyDaoImpl extends AbstractDao<Faculty> implements FacultyDao {

    private static final String TABLE_NAME = "faculty";

    public FacultyDaoImpl(Connection connection) {
        super(connection, new FacultyRowMapper(), TABLE_NAME);
    }

}