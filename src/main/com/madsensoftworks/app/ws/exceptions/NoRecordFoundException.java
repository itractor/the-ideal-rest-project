package com.madsensoftworks.app.ws.exceptions;

public class NoRecordFoundException extends RuntimeException{

    private static final long serialVersionUID = -4691575396480944468L;

    public NoRecordFoundException(String message) {
        super(message);
    }

}
