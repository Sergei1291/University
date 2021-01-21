package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface RegistrationService {

    boolean isApplicantListReady() throws ServiceException;

    boolean isRegistrationFinished() throws ServiceException;

    int findNumRegisteredApplications() throws ServiceException;

}