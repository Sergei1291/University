package com.epam.university.dao.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.AbstractDao;
import com.epam.university.dao.api.SubjectDao;
import com.epam.university.dao.mapper.impl.SubjectRowMapper;
import com.epam.university.model.identifiable.Subject;

import java.sql.Connection;
import java.util.List;

public class SubjectDaoImpl extends AbstractDao<Subject> implements SubjectDao {

    private static final String TABLE_NAME = "subject";

    private static final String FIND_ALL_BY_FACULTY =
            "select subject.id, subject.name from faculty_subject inner join subject " +
                    "on faculty_subject.subject = subject.id where faculty_subject.faculty = ?;";

    public SubjectDaoImpl(Connection connection) {
        super(connection, new SubjectRowMapper(), TABLE_NAME);
    }

    @Override
    public List<Subject> findAllByFaculty(int facultyId) throws DaoException {
        return executeQuery(FIND_ALL_BY_FACULTY, facultyId);
    }

}