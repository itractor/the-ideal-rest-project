package com.madsensoftworks.app.ws.exceptions;

import com.madsensoftworks.app.ws.ui.model.response.ErrorMessage;
import com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRecordFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

    @Override
    public Response toResponse(NoRecordFoundException throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), ErrorMessages.NO_RECORD_FOUND.name(), "Some link");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}

