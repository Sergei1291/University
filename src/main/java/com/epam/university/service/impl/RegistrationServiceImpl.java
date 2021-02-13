package com.epam.university.service.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.helper.DaoHelper;
import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;
    private final static ApplicationStatus ENTERED = ApplicationStatus.ENTERED;
    private final static int MIN_NUMBER_APPLICATIONS = 1;

    protected DaoHelperCreator daoHelperCreator;

    public RegistrationServiceImpl(DaoHelperCreator daoHelperCreator) {
        this.daoHelperCreator = daoHelperCreator;
    }

    @Override
    public boolean isApplicantListReady() throws ServiceException {
        return findNumApplicationsByStatus(ENTERED) >= MIN_NUMBER_APPLICATIONS;
    }

    @Override
    public boolean isRegistrationFinished() throws ServiceException {
        int numberEnteredApplications = findNumApplicationsByStatus(ENTERED);
        int numberRegisteredApplications = findNumApplicationsByStatus(REGISTERED);
        return (numberEnteredApplications + numberRegisteredApplications)
                >= MIN_NUMBER_APPLICATIONS;
    }

    protected int findNumApplicationsByStatus(ApplicationStatus status) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperCreator.create()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findCountByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}