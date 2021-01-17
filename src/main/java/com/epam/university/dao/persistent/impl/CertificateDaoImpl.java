package com.epam.university.dao.persistent.impl;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.mapper.impl.CertificateRowMapper;
import com.epam.university.dao.persistent.api.AbstractPersistentDao;
import com.epam.university.dao.persistent.api.CertificateDao;
import com.epam.university.dao.persistent.extractor.impl.CertificateFieldExtractor;
import com.epam.university.dao.persistent.helper.DaoPersistentHelperImpl;
import com.epam.university.model.identifiable.Certificate;

import java.sql.Connection;
import java.util.List;

public class CertificateDaoImpl extends AbstractPersistentDao<Certificate> implements CertificateDao {

    private static final String TABLE_NAME = "certificate";
    private static final String FUNCTION_SUM = "sum";

    private static final String REMOVE_BY_USER_ID =
            "delete from certificate where user = ?;";
    private static final String FIND_ALL_BY_USER_ID =
            "select * from certificate where user = ?;";
    private static final String FIND_SUM_MARKS_BY_USER_ID =
            "select SUM(mark) as sum from certificate where user = ?;";

    public CertificateDaoImpl(Connection connection) {
        super(connection, new CertificateRowMapper(), TABLE_NAME,
                new CertificateFieldExtractor(), new DaoPersistentHelperImpl());
    }

    @Override
    public void removeByUserId(Integer userId) throws DaoException {
        executeUpdate(REMOVE_BY_USER_ID, userId);
    }

    @Override
    public List<Certificate> findALlByUserId(int userId) throws DaoException {
        return executeQuery(FIND_ALL_BY_USER_ID, userId);
    }

    @Override
    public int findSumMarksByUserId(int userId) throws DaoException {
        List<Integer> results = executeForFunctionResults(FUNCTION_SUM,
                FIND_SUM_MARKS_BY_USER_ID, userId);
        return results.get(0);
    }

}