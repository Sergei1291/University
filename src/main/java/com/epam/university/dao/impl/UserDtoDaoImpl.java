package com.epam.university.dao.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.AbstractDao;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.mapper.impl.UserDtoRowMapper;
import com.epam.university.model.identifiable.UserDto;

import java.sql.Connection;
import java.util.Optional;

public class UserDtoDaoImpl extends AbstractDao<UserDto> implements UserDtoDao {

    private static final String TABLE_NAME = "user";

    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "select * from user where login = ? and password = sha1(?);";

    public UserDtoDaoImpl(Connection connection) {
        super(connection, new UserDtoRowMapper(), TABLE_NAME);
    }

    @Override
    public Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

}