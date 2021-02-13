package com.epam.university.service.api;

import com.epam.university.model.FullApplication;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyApplicantService {

    /**
     * This method is used to finding list of all applications by faculty. The
     * method returns empty list if param facultyId is not valid or applications
     * are not found.
     *
     * @param facultyId
     * @return
     * @throws ServiceException
     */
    List<FullApplication> findFacultyApplicationsInfo(int facultyId) throws ServiceException;

}