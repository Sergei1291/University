package com.epam.university.dao;

public class DaoException extends Exception {

    public DaoException(Exception e) {

        super(e);

    }

    public DaoException(String message) {

        super(message);

    }

    public DaoException(String message, Exception e) {

        super(message, e);

    }

}