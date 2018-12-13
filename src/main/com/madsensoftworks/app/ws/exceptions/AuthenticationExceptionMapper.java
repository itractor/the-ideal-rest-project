package com.madsensoftworks.app.ws.exceptions;

import com.madsensoftworks.app.ws.ui.model.response.ErrorMessage;
import com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<Throwable> {

        @Override
        public Response toResponse(Throwable throwable) {
            ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(),
                    ErrorMessages.AUTHENTICATION_FAILED.name(),
                    "Some link");

            return Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();
        }


}
