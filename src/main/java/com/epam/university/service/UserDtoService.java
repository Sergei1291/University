package com.epam.university.service;

import com.epam.university.model.user.UserDto;

import java.util.Optional;

public interface UserDtoService {

    boolean isRegistrationFinished() throws ServiceException;

    Optional<UserDto> login(String login, String password) throws ServiceException;

}