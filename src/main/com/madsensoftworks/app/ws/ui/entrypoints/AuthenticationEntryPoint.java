package com.madsensoftworks.app.ws.ui.entrypoints;

import com.madsensoftworks.app.ws.service.AuthenticationService;
import com.madsensoftworks.app.ws.service.AuthenticationServiceImpl;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;
import com.madsensoftworks.app.ws.ui.model.request.LoginCredentials;
import com.madsensoftworks.app.ws.ui.model.response.AuthenticationDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials) {
        AuthenticationDetails returnValue = new AuthenticationDetails();

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserDTO authenticatedUser = authenticationService.authenticate(loginCredentials.getUserName(), loginCredentials.getUserPassword());

        //Reset access token
        authenticationService.resetSecurityCredentials(loginCredentials.getUserPassword(), authenticatedUser);
        String accessToken = authenticationService.issueAccessToken(authenticatedUser);

        returnValue.setId(authenticatedUser.getPublicUserId());
        returnValue.setToken(accessToken);

        return returnValue;
    }
}
