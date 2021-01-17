package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.UserDtoDao;
import com.epam.university.model.identifiable.user.ApplicationStatus;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;
    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;
    private final static int MIN_NUMBER_PROCESSED_USERS = 1;

    protected DaoHelperCreator daoHelperCreator;

    public RegistrationServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public boolean isRegistrationFinished() throws ServiceException {
        int numberUsers = findNumProcessedApplications();
        return numberUsers >= MIN_NUMBER_PROCESSED_USERS;
    }

    @Override
    public int findNumProcessedApplications() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            UserDtoDao userDtoDao = daoHelper.createUserDtoDao();
            int registeredApplications = userDtoDao.findNumberUsersByApplicationStatus(REGISTERED);
            int enteredApplications = userDtoDao.findNumberUsersByApplicationStatus(ENTERED);
            return registeredApplications + enteredApplications;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}