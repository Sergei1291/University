package com.epam.university.service.api;

import com.epam.university.model.identifiable.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Map;

public interface EnrolleeService extends RegistrationService {

    boolean apply(UserDto userDto, int facultyId, int averageMark,
                  Map<Integer, Integer> subjectsMarks) throws ServiceException;

    boolean cancelApplication(UserDto userDto) throws ServiceException;

}