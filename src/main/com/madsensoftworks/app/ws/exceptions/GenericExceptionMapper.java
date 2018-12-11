package main.com.madsensoftworks.app.ws.exceptions;

import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessage;
import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), ErrorMessages.INTERNAL_SERVER_ERROR.name(), "Some link");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
