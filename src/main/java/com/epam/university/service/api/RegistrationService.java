package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface RegistrationService {

    boolean isRegistrationFinished() throws ServiceException;

    int findNumProcessedApplications() throws ServiceException;

}