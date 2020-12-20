package com.epam.university.dao;

import com.epam.university.model.Subject;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {

    List<Subject> findByFaculty(int idFaculty) throws DaoException;

}