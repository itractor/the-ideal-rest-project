package main.com.madsensoftworks.app.ws.io.dao;

import main.com.madsensoftworks.app.ws.shared.dto.UserDTO;

public interface DAO {
    void openConnection();
    UserDTO getUserByUserName(String userName);
    UserDTO saveUser(UserDTO user);
    void closeConnection();
}
