package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Map;

public interface ApplicationService extends RegistrationService {

    /**
     * This method is used to submit an application by the user. Application
     * is applying to faculty with id equal param facultyId. Applied application
     * must have average mark equal param averageMark. Subjects certificates must
     * appear in accordance to faculty and param subjectsMarks. Param subjectsMarks
     * must have three keys and values: key - id subject, value - subject mark.
     * This method returns true if submission of application was successful, else
     * (invalid params, user already have application) method returns false.
     *
     * @param userDto
     * @param facultyId
     * @param averageMark
     * @param subjectsMarks
     * @return
     * @throws ServiceException
     */
    boolean apply(UserDto userDto, int facultyId, int averageMark,
                  Map<Integer, Integer> subjectsMarks) throws ServiceException;

}