package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface RegistrationService {

    /**
     * This method is used to checking of ready lists of entered users.
     * The method returns true if data warehouse has one or more applications
     * with status 'entered'.
     *
     * @return
     * @throws ServiceException
     */
    boolean isApplicantListReady() throws ServiceException;

    /**
     * This method is used to checking of finish registration of applications.
     * The method returns true if data warehouse has one or more applications
     * with statuses 'registered' or 'entered'.
     *
     * @return
     * @throws ServiceException
     */
    boolean isRegistrationFinished() throws ServiceException;

}