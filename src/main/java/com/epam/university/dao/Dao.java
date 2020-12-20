package com.epam.university.dao;

import com.epam.university.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> findById(Integer id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T identifiable) throws DaoException;

    void removeById(Integer id) throws DaoException;

}