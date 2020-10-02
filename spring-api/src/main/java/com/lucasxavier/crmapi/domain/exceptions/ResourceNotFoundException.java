package com.lucasxavier.crmapi.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2271785151879063763L;

    public ResourceNotFoundException(Object id) {
        super("Recurso não encontrado. Id: " + id);
    }
}
