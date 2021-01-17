package com.epam.university.connection;

public class ConnectionException extends RuntimeException {

    public ConnectionException(String message, Exception e) {
        super(message, e);
    }

}