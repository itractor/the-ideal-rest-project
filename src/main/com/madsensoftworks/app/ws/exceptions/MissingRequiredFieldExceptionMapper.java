package com.madsensoftworks.app.ws.exceptions;

import com.madsensoftworks.app.ws.ui.model.response.ErrorMessage;
import com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingRequiredFieldExceptionMapper implements ExceptionMapper<MissingRequiredFieldException> {

    @Override
    public Response toResponse(MissingRequiredFieldException throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), ErrorMessages.MISSING_REQUIRED_FIELD.name(), "Some link");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
