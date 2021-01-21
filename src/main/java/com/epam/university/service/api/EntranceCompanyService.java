package com.epam.university.service.api;

import com.epam.university.service.ServiceException;

public interface EntranceCompanyService extends FacultyService {

    int findNumberApplicationsByFaculty(int facultyId) throws ServiceException;

}