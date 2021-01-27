package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.model.identifiable.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoRowMapper implements RowMapper<UserDto> {

    private final static String ID_COLUMN = "id";
    private final static String ROLE_COLUMN = "role";
    private final static String NAME_COLUMN = "name";
    private final static String SURNAME_COLUMN = "surname";

    @Override
    public UserDto map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        UserRole role = findUserRole(resultSet);
        String name = resultSet.getString(NAME_COLUMN);
        String surname = resultSet.getString(SURNAME_COLUMN);
        return new UserDto(id, role, name, surname);
    }

    private UserRole findUserRole(ResultSet resultSet) throws SQLException {
        String roleValue = resultSet.getString(ROLE_COLUMN);
        String roleUpperCase = roleValue.toUpperCase();
        return UserRole.valueOf(roleUpperCase);
    }

}