package com.epam.university.dao.impl;

import com.epam.university.dao.AbstractDao;
import com.epam.university.dao.CertificateDao;
import com.epam.university.dao.DaoException;
import com.epam.university.mapper.impl.CertificateRowMapper;
import com.epam.university.model.Certificate;

import java.sql.Connection;
import java.util.List;

public class CertificateDaoImpl extends AbstractDao<Certificate> implements CertificateDao {

    private static final String TABLE_NAME = "certificate";

    private static final String SAVE =
            "insert into certificate (user, subject, mark) values (?, ?, ?);";
    private static final String REMOVE_BY_USER =
            "delete from certificate where user = ?;";
    private static final String FIND_ALL_BY_USER =
            "select * from certificate where user = ?;";

    public CertificateDaoImpl(Connection connection) {

        super(connection, new CertificateRowMapper(), TABLE_NAME);

    }

    @Override
    public void save(Certificate certificate) throws DaoException {

        int user = certificate.getUser();
        int subject = certificate.getSubject();
        int mark = certificate.getMark();

        executeUpdate(SAVE, user, subject, mark);

    }

    @Override
    public void removeByUser(Integer userId) throws DaoException {

        executeUpdate(REMOVE_BY_USER, userId);

    }

    @Override
    public List<Certificate> findALlByUser(int userId) throws DaoException {

        return executeQuery(FIND_ALL_BY_USER, userId);
    }

}