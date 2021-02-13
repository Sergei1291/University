package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface EnteredUserService extends RegistrationService {

    /**
     * This method is used to finding lists of entered applicants by faculty.
     * The method returns empty list if param facultyId is not valid or users
     * are not found.
     *
     * @param facultyId
     * @return
     * @throws ServiceException
     */
    List<UserDto> findEnteredUsersByFaculty(int facultyId) throws ServiceException;

}