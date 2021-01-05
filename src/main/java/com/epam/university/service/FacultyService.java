package com.epam.university.service;

import com.epam.university.model.Faculty;
import com.epam.university.model.FacultyDto;
import com.epam.university.model.Subject;

import java.util.List;

public interface FacultyService {

    List<Faculty> findFaculties() throws ServiceException;

    List<FacultyDto> findFacultiesDto() throws ServiceException;

    List<Subject> findSubjects(int idFaculty) throws ServiceException;

    FacultyDto findFaculty(int idFaculty) throws ServiceException;

}