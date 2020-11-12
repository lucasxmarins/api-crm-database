package com.lucasxavier.crmapi.domain.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 3765975589844884741L;

    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
}
