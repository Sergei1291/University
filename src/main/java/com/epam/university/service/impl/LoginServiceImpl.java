package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.LoginService;
import com.epam.university.validator.AuthorizationValidator;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private final DaoHelperCreator daoHelperCreator;
    private final AuthorizationValidator authorizationValidator;

    public LoginServiceImpl(DaoHelperCreator daoHelperCreator, AuthorizationValidator authorizationValidator) {
        this.daoHelperCreator = daoHelperCreator;
        this.authorizationValidator = authorizationValidator;
    }

    @Override
    public Optional<UserDto> login(String login, String password) throws ServiceException {
        if (!authorizationValidator.isValid(login, password)) {
            return Optional.empty();
        }
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            return userDtoDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}