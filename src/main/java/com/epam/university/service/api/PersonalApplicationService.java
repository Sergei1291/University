package com.epam.university.service.api;

import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Optional;

public interface PersonalApplicationService extends RegistrationService {

    Optional<FullApplication> findUserApplication(UserDto userDto) throws ServiceException;

}