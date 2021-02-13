package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Certificate;

import java.util.List;

public interface CertificateDao extends PersistentDao<Certificate> {

    /**
     * This method is used to find all objects type Certificate by parameter
     * having name application and equal applicationId from data warehouse.
     * This method will return empty list, if data warehouse does not contain
     * objects with equal applicationId.
     *
     * @param applicationId
     * @return
     * @throws DaoException
     */
    List<Certificate> findALlByApplication(int applicationId) throws DaoException;

    /**
     * This method is used to find sum marks all certificates where parameter
     * having name application and equal applicationId from data warehouse.
     * This method will return zero, if data warehouse does not contain
     * objects with equal applicationId.
     *
     * @param applicationId
     * @return
     * @throws DaoException
     */
    int findSumMarksByApplication(int applicationId) throws DaoException;

}