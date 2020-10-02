package com.lucasxavier.crmapi.domain.exceptions;

public class InvalidDataException extends RuntimeException{

    private static final long serialVersionUID = 6936296917798237094L;

    public InvalidDataException(String msg){
        super(msg);
    }
}
