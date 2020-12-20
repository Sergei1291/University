package com.epam.university.dao;

import com.epam.university.model.Certificate;

import java.util.List;

public interface CertificateDao extends Dao<Certificate> {

    void removeByUser(Integer userId) throws DaoException;

    List<Certificate> findALlByUser(int userId) throws DaoException;

}