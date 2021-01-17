package com.epam.university.service.api;

import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Optional;

public interface LoginService {

    Optional<UserDto> login(String login, String password) throws ServiceException;

}