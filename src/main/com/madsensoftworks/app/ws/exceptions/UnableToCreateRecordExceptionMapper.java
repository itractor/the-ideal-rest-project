package main.com.madsensoftworks.app.ws.exceptions;

import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessage;
import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnableToCreateRecordExceptionMapper implements ExceptionMapper<UnableToCreateRecordException> {

    @Override
    public Response toResponse(UnableToCreateRecordException throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), ErrorMessages.RECORD_ALREADY_EXISTS.name(), "Some link");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
