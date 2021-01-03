package com.epam.university.dao.helper;

import com.epam.university.connection.ConnectionPool;
import com.epam.university.connection.ProxyConnection;
import com.epam.university.dao.*;
import com.epam.university.dao.impl.CertificateDaoImpl;
import com.epam.university.dao.impl.FacultyDtoDaoImpl;
import com.epam.university.dao.impl.SubjectDaoImpl;
import com.epam.university.dao.impl.UserDtoDaoImpl;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection proxyConnection;

    public DaoHelper(ConnectionPool pool) {

        this.proxyConnection = pool.getConnection();

    }

    public UserDtoDao createUserDtoDao() {

        return new UserDtoDaoImpl(proxyConnection);
    }

    public FacultyDtoDao createFacultyDtoDao() {

        return new FacultyDtoDaoImpl(proxyConnection);
    }

    public SubjectDao createSubjectDao() {

        return new SubjectDaoImpl(proxyConnection);
    }

    public CertificateDao createCertificateDao() {

        return new CertificateDaoImpl(proxyConnection);
    }

    @Override
    public void close() {

        proxyConnection.close();

    }

    public void startTransaction() throws DaoException {

        try {
            proxyConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    public void commitTransaction() throws DaoException {

        try {
            proxyConnection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    public void rollBackTransaction() throws DaoException {

        try {
            proxyConnection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    public void finishTransaction() throws DaoException {

        try {
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

}