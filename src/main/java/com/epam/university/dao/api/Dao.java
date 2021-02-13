package com.epam.university.dao.api;

import com.epam.university.dao.DaoException;
import com.epam.university.model.identifiable.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    /**
     * This method is used to find object by id from data warehouse. This method
     * will return empty optional, if data warehouse does not contain object with
     * equal id.
     *
     * @param id
     * @return
     * @throws DaoException
     */
    Optional<T> findById(Integer id) throws DaoException;

    /**
     * This method is used to find all objects from data warehouse. This method
     * will return empty list, if data warehouse does not contain objects T type.
     *
     * @return
     * @throws DaoException
     */
    List<T> findAll() throws DaoException;

    /**
     * This method is used to remove object by id from data warehouse.
     *
     * @param id
     * @throws DaoException
     */
    void removeById(Integer id) throws DaoException;

}