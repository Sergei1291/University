package com.epam.university.dao;

import com.epam.university.mapper.SubjectRowMapper;
import com.epam.university.model.Subject;

import java.sql.Connection;
import java.util.List;

public class SubjectDaoImpl extends AbstractDao<Subject> implements SubjectDao {

    private static final String FIND_BY_FACULTY =
            "select subject.id, subject.name from faculty_requirement inner join subject on faculty_requirement.subject = subject.id where faculty_requirement.faculty = ?;";

    private static final String TABLE_NAME = "subject";

    public SubjectDaoImpl(Connection connection) {

        super(connection, new SubjectRowMapper(), TABLE_NAME);

    }

    @Override
    public List<Subject> findByFaculty(int idFaculty) throws DaoException {

        return executeQuery(FIND_BY_FACULTY, idFaculty);
    }

}