package com.epam.university.service.api;

import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface EnteredApplicantService extends RegistrationService {

    List<UserDto> findEnteredApplicantsByFaculty(int facultyId) throws ServiceException;

}