package com.epam.university.service;

public class ServiceException extends Exception {

    public ServiceException(Exception e) {

        super(e);

    }

    public ServiceException(String message, Exception e) {

        super(message, e);

    }

}