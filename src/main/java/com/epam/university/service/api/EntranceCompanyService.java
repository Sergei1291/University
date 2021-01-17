package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface EntranceCompanyService extends FacultyService {

    int findNumberApplicantsByFaculty(int facultyId) throws ServiceException;

}