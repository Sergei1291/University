package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Subject;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {

    List<Subject> findAllByFaculty(int facultyId) throws DaoException;

}