package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Certificate;

import java.util.List;

public interface CertificateDao extends PersistentDao<Certificate> {

    List<Certificate> findALlByApplication(int applicationId) throws DaoException;

    int findSumMarksByApplication(int applicationId) throws DaoException;

}