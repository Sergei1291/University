package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> findById(Integer id) throws DaoException;

    List<T> findAll() throws DaoException;

    void removeById(Integer id) throws DaoException;

}