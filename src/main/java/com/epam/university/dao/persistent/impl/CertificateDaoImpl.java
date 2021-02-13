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

    private static final String FIND_ALL_BY_APPLICATION =
            "select * from certificate where application = ?;";
    private static final String FIND_SUM_MARKS_BY_APPLICATION =
            "select SUM(mark) as sum from certificate where application = ?;";

    public CertificateDaoImpl(Connection connection) {
        super(connection, new CertificateRowMapper(), TABLE_NAME,
                new CertificateFieldExtractor(), new DaoPersistentHelperImpl());
    }

    @Override
    public List<Certificate> findALlByApplication(int applicationId) throws DaoException {
        return executeQuery(FIND_ALL_BY_APPLICATION, applicationId);
    }

    @Override
    public int findSumMarksByApplication(int applicationId) throws DaoException {
        return executeForSingleFunctionResult(FUNCTION_SUM,
                FIND_SUM_MARKS_BY_APPLICATION, applicationId);
    }

}