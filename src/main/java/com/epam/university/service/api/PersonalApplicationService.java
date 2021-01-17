package com.epam.university.service.api;

import com.epam.university.model.identifiable.Certificate;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface PersonalApplicationService extends RegistrationService {

    List<Certificate> findUserCertificates(int userId) throws ServiceException;

}