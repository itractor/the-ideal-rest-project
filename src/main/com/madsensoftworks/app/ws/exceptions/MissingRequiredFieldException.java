package main.com.madsensoftworks.app.ws.exceptions;

public class MissingRequiredFieldException extends RuntimeException {

    private static final long serialVersionUID = -7533983379196210595L;

    public MissingRequiredFieldException(String message) {
        super(message);
    }
}
