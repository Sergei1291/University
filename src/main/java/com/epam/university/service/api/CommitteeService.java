package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface CommitteeService extends RegistrationService {

    /**
     * This method is used to closing registration of applications. The method must
     * set up all applied applications status 'registered'.
     *
     * @throws ServiceException
     */
    void closeRegistration() throws ServiceException;

    /**
     * This method is used to forming lists of entered applicants. The method must
     * set up all registered applications status 'entered' or 'unentered'.
     *
     * @throws ServiceException
     */
    void formListApplicants() throws ServiceException;

}