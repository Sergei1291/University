package com.epam.university.service.api;

import com.epam.university.model.FullApplication;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyApplicantService {

    List<FullApplication> findFacultyApplicationsInfo(int facultyId) throws ServiceException;

}