package com.madsensoftworks.app.ws.service;

import com.madsensoftworks.app.ws.shared.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO user);
    UserDTO getUser(String id);

}
