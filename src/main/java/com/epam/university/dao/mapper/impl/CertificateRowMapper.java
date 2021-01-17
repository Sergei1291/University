package com.epam.university.dao.mapper.impl;

import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.model.identifiable.Certificate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateRowMapper implements RowMapper<Certificate> {

    private final static String ID_COLUMN = "id";
    private final static String USER_COLUMN = "user";
    private final static String SUBJECT_COLUMN = "subject";
    private final static String MARK_COLUMN = "mark";

    @Override
    public Certificate map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        int user = resultSet.getInt(USER_COLUMN);
        int subject = resultSet.getInt(SUBJECT_COLUMN);
        int mark = resultSet.getInt(MARK_COLUMN);
        return new Certificate(id, user, subject, mark);
    }

}