package com.madsensoftworks.app.ws.exceptions;

public class AuthenticationException extends RuntimeException{

    private static final long serialVersionUID = -6435432948933724956L;

    public AuthenticationException(String message) {
        super(message);
    }
}
