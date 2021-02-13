package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Optional;

public interface LoginService {

    /**
     * This method is used to finding user in data warehouse by login and password.
     * The method returns empty optional if params login or password are not valid,
     * or user was not found.
     *
     * @return
     * @throws ServiceException
     */
    Optional<UserDto> login(String login, String password) throws ServiceException;

}