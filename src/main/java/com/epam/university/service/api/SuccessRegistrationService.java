package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface SuccessRegistrationService extends RegistrationService {

    /**
     * This method is used to finding number registered applications.
     *
     * @return
     * @throws ServiceException
     */
    int findNumRegisteredApplications() throws ServiceException;

}