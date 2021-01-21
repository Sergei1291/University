package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface EnteredUserService extends RegistrationService {

    List<UserDto> findEnteredUsersByFaculty(int facultyId) throws ServiceException;

}