package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Subject;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {

    /**
     * This method is used to find all objects type Subject by parameter having
     * name faculty and equal facultyId from data warehouse. This method will
     * return empty list, if data warehouse does not contain objects with equal
     * facultyId.
     *
     * @param facultyId
     * @return
     * @throws DaoException
     */
    List<Subject> findAllByFaculty(int facultyId) throws DaoException;

}