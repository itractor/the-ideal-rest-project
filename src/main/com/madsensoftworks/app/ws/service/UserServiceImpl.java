package main.com.madsensoftworks.app.ws.service;

import main.com.madsensoftworks.app.ws.exceptions.UnableToCreateRecordException;
import main.com.madsensoftworks.app.ws.io.dao.DAO;
import main.com.madsensoftworks.app.ws.io.dao.implementation.MySQLDAO;
import main.com.madsensoftworks.app.ws.shared.dto.UserDTO;
import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;
import main.com.madsensoftworks.app.ws.utils.UserProfileUtils;

public class UserServiceImpl implements UserService{

    DAO database;

    public UserServiceImpl() {
        this.database = new MySQLDAO();
    }

    UserProfileUtils userProfileUtils = new UserProfileUtils();

    @Override
    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = new UserDTO();

        //Validate required fields
        userProfileUtils.validateRequiredFields(user);

        //Check if user exists
        UserDTO existingUser = this.getUserByUserName(user.getEmail());
        if(existingUser != null) {
            throw new UnableToCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.name());
        }

        //Generate secure public user id

        //Generate salt

        //Generate secure password

        //Record data into database

        //Return the user profile

        return returnValue;
    }

    private UserDTO getUserByUserName(String userName) {
        UserDTO userDto = null;

        if (userName == null || userName.isEmpty()) {
            return userDto;
        }

        try {
            this.database.openConnection();
            userDto = this.database.getUserByUserName(userName);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }
}
