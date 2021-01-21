package com.epam.university.dao.helper;

import com.epam.university.connection.ConnectionPool;
import com.epam.university.connection.ProxyConnection;
import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.FacultyDtoDao;
import com.epam.university.dao.api.SubjectDao;
import com.epam.university.dao.api.UserDtoDao;
import com.epam.university.dao.impl.FacultyDtoDaoImpl;
import com.epam.university.dao.impl.SubjectDaoImpl;
import com.epam.university.dao.impl.UserDtoDaoImpl;
import com.epam.university.dao.persistent.api.ApplicationDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.dao.persistent.impl.ApplicationDaoImpl;
import com.epam.university.dao.persistent.impl.CertificateDaoImpl;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection proxyConnection;

    public DaoHelper(ConnectionPool pool) {
        this.proxyConnection = pool.getConnection();
    }

    public ApplicationDao createApplicationDao() {
        return new ApplicationDaoImpl(proxyConnection);
    }

    public CertificateDao createCertificateDao() {
        return new CertificateDaoImpl(proxyConnection);
    }

    public FacultyDtoDao createFacultyDtoDao() {
        return new FacultyDtoDaoImpl(proxyConnection);
    }

    public SubjectDao createSubjectDao() {
        return new SubjectDaoImpl(proxyConnection);
    }

    public UserDtoDao createUserDtoDao() {
        return new UserDtoDaoImpl(proxyConnection);
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