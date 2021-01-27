package com.epam.university.service.api;

import com.epam.university.model.FullApplication;
import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Optional;

public interface PersonalApplicationService extends RegistrationService {

    /**
     * This method is used to finding user's application. The method returns
     * empty optional if param userDto is not valid, or application was not found.
     *
     * @param userDto
     * @return
     * @throws ServiceException
     */
    Optional<FullApplication> findUserApplication(UserDto userDto) throws ServiceException;

}