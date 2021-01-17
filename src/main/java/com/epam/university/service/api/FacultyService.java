package com.epam.university.service.api;

import com.epam.university.model.identifiable.Faculty;
import com.epam.university.model.identifiable.Subject;
import com.epam.university.service.ServiceException;

import java.util.List;

public interface FacultyService extends FacultyDtoService {

    List<Faculty> findFaculties() throws ServiceException;

    List<Subject> findFacultySubjects(int facultyId) throws ServiceException;

}