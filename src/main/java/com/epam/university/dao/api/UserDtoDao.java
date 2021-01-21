package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.UserDto;

import java.util.Optional;

public interface UserDtoDao extends Dao<UserDto> {

    Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException;

}