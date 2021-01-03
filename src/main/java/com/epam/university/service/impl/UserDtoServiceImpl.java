package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.UserDtoDao;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.user.ApplicationStatus;
import com.epam.university.model.user.UserDto;
import com.epam.university.service.AbstractService;
import com.epam.university.service.ServiceException;
import com.epam.university.service.UserDtoService;

import java.util.Optional;

public class UserDtoServiceImpl extends AbstractService implements UserDtoService {

    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;
    private final static int MIN_NUMBER_REGISTERED_USERS = 1;

    private DaoHelperCreator daoHelperCreator;

    public UserDtoServiceImpl(DaoHelperCreator daoHelperCreator) {

        this.daoHelperCreator = daoHelperCreator;

    }

    @Override
    public boolean isRegistrationFinished() throws ServiceException {

        try (DaoHelper daoHelper = daoHelperCreator.create()) {

            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();

            int numberUsers = userDtoDao.findNumberUsersApplicationStatus(REGISTERED);

            if (numberUsers >= MIN_NUMBER_REGISTERED_USERS) {
                return true;
            }

            return false;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

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