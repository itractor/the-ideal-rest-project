package com.madsensoftworks.app.ws.exceptions;

public class UnableToCreateRecordException extends RuntimeException {

    private static final long serialVersionUID = 5600226880165452703L;

    public UnableToCreateRecordException(String message) {
        super(message);
    }
}
