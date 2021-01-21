package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.LoginService;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private DaoHelperCreator daoHelperCreator;

    public LoginServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public Optional<UserDto> login(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            return userDtoDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}