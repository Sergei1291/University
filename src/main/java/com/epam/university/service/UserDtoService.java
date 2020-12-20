package com.epam.university.service;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.model.UserDto;

import java.util.Optional;

public class UserDtoService {

    private DaoHelperCreator daoHelperCreator;

    public UserDtoService(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    public Optional<UserDto> login(String login, String password) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            return userDtoDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

}