package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.Dao;
import com.epam.university.model.identifiable.Identifiable;

public interface PersistentDao<T extends Identifiable> extends Dao<T> {

    void save(T identifiable) throws DaoException;

}