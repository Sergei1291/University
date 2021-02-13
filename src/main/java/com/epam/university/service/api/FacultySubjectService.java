package com.epam.university.service.api;

import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultySubjectService {

    /**
     * This method is used to finding list of all subjects by faculty.
     *
     * @return
     * @throws ServiceException
     */
    List<Subject> findFacultySubjects(int facultyId) throws ServiceException;

}