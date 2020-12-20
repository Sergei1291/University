package com.epam.university.mapper;

import com.epam.university.model.ApplicationStatus;
import com.epam.university.model.Role;
import com.epam.university.model.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoRowMapper implements RowMapper<UserDto> {

    @Override
    public UserDto map(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");

        String roleValue = resultSet.getString("role");
        String roleUpperCase = roleValue.toUpperCase();
        Role role = Role.valueOf(roleUpperCase);

        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");

        Integer faculty = resultSet.getInt("faculty");
        Integer averageMark = resultSet.getInt("average_mark");

        String applicationStatusValue = resultSet.getString("application_status");
        ApplicationStatus applicationStatus;
        if (applicationStatusValue != null) {
            String statusUpperCase = applicationStatusValue.toUpperCase();
            applicationStatus = ApplicationStatus.valueOf(statusUpperCase);
        } else {
            applicationStatus = null;
        }
        
        return new UserDto(id, role, name, surname, faculty, averageMark, applicationStatus);
    }

}