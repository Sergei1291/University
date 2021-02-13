package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.UserDto;

import java.util.Optional;

public interface UserDtoDao extends Dao<UserDto> {

    /**
     * This method is used to find object type UserDto by parameters having
     * name login, password and equal values login, password from data warehouse.
     * This method will return empty optional, if data warehouse does not contain
     * object with equal values login and password.
     *
     * @param login
     * @param password
     * @return
     * @throws DaoException
     */
    Optional<UserDto> findByLoginAndPassword(String login, String password) throws DaoException;

}