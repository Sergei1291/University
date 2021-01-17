package com.epam.university.service.api;

import com.epam.university.model.identifiable.Certificate;
import com.epam.university.model.identifiable.user.UserDto;
import com.epam.university.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface FacultyApplicantService {

    Map<UserDto, List<Certificate>> findFacultyApplicantsInfo(int facultyId) throws ServiceException;

}