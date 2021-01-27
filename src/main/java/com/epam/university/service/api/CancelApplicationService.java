package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

public interface CancelApplicationService extends RegistrationService {

    /**
     * This method is used to cancel an application by the user. In result of method
     * user's application will be have status 'cancelled'. This method returns true
     * if cancel of application was successful. This method returns false if user's
     * application was not found or param userDto is not valid.
     *
     * @param userDto
     * @return
     * @throws ServiceException
     */
    boolean cancelApplication(UserDto userDto) throws ServiceException;

}