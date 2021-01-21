package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.Application;
import com.epam.university.model.identifiable.ApplicationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationRowMapper implements RowMapper<Application> {

    private final static String ID_COLUMN = "id";
    private final static String USER_COLUMN = "user";
    private final static String FACULTY_COLUMN = "faculty";
    private final static String AVERAGE_MARK_COLUMN = "average_mark";
    private final static String STATUS_COLUMN = "status";

    @Override
    public Application map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        int user = resultSet.getInt(USER_COLUMN);
        int faculty = resultSet.getInt(FACULTY_COLUMN);
        int averageMark = resultSet.getInt(AVERAGE_MARK_COLUMN);
        ApplicationStatus status = findApplicationStatus(resultSet);
        return new Application(id, user, faculty, averageMark, status);
    }

    private ApplicationStatus findApplicationStatus(ResultSet resultSet) throws SQLException {
        String applicationStatusValue = resultSet.getString(STATUS_COLUMN);
        String statusUpperCase = applicationStatusValue.toUpperCase();
        return ApplicationStatus.valueOf(statusUpperCase);
    }

}