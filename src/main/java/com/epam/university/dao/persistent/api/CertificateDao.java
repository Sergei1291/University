package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Certificate;

import java.util.List;

public interface CertificateDao extends PersistentDao<Certificate> {

    void removeByUserId(Integer userId) throws DaoException;

    List<Certificate> findALlByUserId(int userId) throws DaoException;

    int findSumMarksByUserId(int userId) throws DaoException;

}