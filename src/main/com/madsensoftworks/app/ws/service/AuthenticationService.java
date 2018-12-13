package com.madsensoftworks.app.ws.service;

import com.madsensoftworks.app.ws.exceptions.AuthenticationException;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;

public interface AuthenticationService {
    UserDTO authenticate(String userName, String Password) throws AuthenticationException;
    String issueAccessToken(UserDTO userProfile) throws AuthenticationException;
    void resetSecurityCredentials(String userPassword, UserDTO userProfile);
}
