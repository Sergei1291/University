package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface CommitteeService extends RegistrationService {

    void closeRegistration() throws ServiceException;

    void formListApplicants() throws ServiceException;

}