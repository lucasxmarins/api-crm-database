package com.lucasxavier.crmapi.domain.exceptions;

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 3265578139565892369L;

    public DatabaseException(String msg) {
        super(msg);
    }

}