package com.epam.university.service.impl;

import com.epam.university.dao.helper.DaoHelperCreator;
import com.epam.university.model.identifiable.ApplicationStatus;
import com.epam.university.service.ServiceException;
import com.epam.university.service.api.SuccessRegistrationService;

public class SuccessRegistrationServiceImpl extends RegistrationServiceImpl
        implements SuccessRegistrationService {

    private final static ApplicationStatus REGISTERED = ApplicationStatus.REGISTERED;

    public SuccessRegistrationServiceImpl(DaoHelperCreator daoHelperCreator) {
        super(daoHelperCreator);
    }

    @Override
    public int findNumRegisteredApplications() throws ServiceException {
        return findNumApplicationsByStatus(REGISTERED);
    }

}