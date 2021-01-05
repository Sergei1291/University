package com.epam.university.service;

import com.epam.university.model.user.UserDto;
import com.epam.university.model.user.Enrollee;

import java.util.List;
import java.util.Map;

public interface ApplicationService {

    boolean isRegistrationFinished() throws ServiceException;

    UserDto apply(int userId, int facultyId, int averageMark,
                  Map<Integer, Integer> subjectsMarks) throws ServiceException;

    UserDto cancelApplication(int userId) throws ServiceException;

    int registerApplications() throws ServiceException;

    List<Enrollee> findEnrolledApplicantsByFaculty(int idFaculty) throws ServiceException;

}