package com.epam.university.service.api;

import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;

import java.util.Map;

public interface EnrolleeService extends RegistrationService {

    UserDto apply(UserDto userDto, int facultyId, int averageMark,
                  Map<Integer, Integer> subjectsMarks) throws ServiceException;

    UserDto cancelApplication(UserDto userDto) throws ServiceException;

}