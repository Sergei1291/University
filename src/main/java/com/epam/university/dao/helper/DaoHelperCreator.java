package com.epam.university.dao.helper;

import com.epam.university.connection.ConnectionPool;

public class DaoHelperCreator {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }

}