package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.model.identifiable.user.Role;
import com.epam.university.model.identifiable.user.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoRowMapper implements RowMapper<UserDto> {

    private final static String ID_COLUMN = "id";
    private final static String ROLE_COLUMN = "role";
    private final static String NAME_COLUMN = "name";
    private final static String SURNAME_COLUMN = "surname";
    private final static String FACULTY_COLUMN = "faculty";
    private final static String AVERAGE_MARK_COLUMN = "average_mark";
    private final static String APPLICATION_STATUS_COLUMN = "application_status";

    @Override
    public UserDto map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        Role role = findRole(resultSet);
        String name = resultSet.getString(NAME_COLUMN);
        String surname = resultSet.getString(SURNAME_COLUMN);
        Integer faculty = resultSet.getInt(FACULTY_COLUMN);
        Integer averageMark = resultSet.getInt(AVERAGE_MARK_COLUMN);
        ApplicationStatus applicationStatus = findApplicationStatus(resultSet);
        return new UserDto(id, role, name, surname, faculty, averageMark, applicationStatus);
    }

    private Role findRole(ResultSet resultSet) throws SQLException {
        String roleValue = resultSet.getString(ROLE_COLUMN);
        String roleUpperCase = roleValue.toUpperCase();
        return Role.valueOf(roleUpperCase);
    }

    private ApplicationStatus findApplicationStatus(ResultSet resultSet) throws SQLException {
        String applicationStatusValue = resultSet.getString(APPLICATION_STATUS_COLUMN);
        if (applicationStatusValue == null) {
            return null;
        }
        String statusUpperCase = applicationStatusValue.toUpperCase();
        return ApplicationStatus.valueOf(statusUpperCase);
    }

}