package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.AbstractService;
import com.epam.university.service.ServiceException;
import com.epam.university.service.UserDtoService;

import java.util.Optional;

public class UserDtoServiceImpl extends AbstractService implements UserDtoService {

    private DaoHelperCreator daoHelperCreator;

    public UserDtoServiceImpl(DaoHelperCreator daoHelperCreator) {

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