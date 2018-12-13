package com.madsensoftworks.app.ws.service;

import com.madsensoftworks.app.ws.exceptions.NoRecordFoundException;
import com.madsensoftworks.app.ws.exceptions.UnableToCreateRecordException;
import com.madsensoftworks.app.ws.io.dao.DAO;
import com.madsensoftworks.app.ws.io.dao.implementation.MySQLDAO;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;
import com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;
import com.madsensoftworks.app.ws.utils.UserProfileUtils;

public class UserServiceImpl implements com.madsensoftworks.app.ws.service.UserService {

    DAO database;

    public UserServiceImpl() {
        this.database = new MySQLDAO();
    }

    UserProfileUtils userProfileUtils = new UserProfileUtils();

    @Override
    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = null;

        //Validate required fields
        userProfileUtils.validateRequiredFields(user);

        //Check if user exists
        UserDTO existingUser = this.getUserByUserName(user.getEmail());
        if(existingUser != null) {
            throw new UnableToCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.name());
        }

        //Generate secure public user id
        String publicUserId = userProfileUtils.generateUserId(30);
        user.setPublicUserId(publicUserId);

        //Generate salt
        String salt = userProfileUtils.getSalt(30);

        //Generate secure password
        String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setEncryptedPassword(encryptedPassword);

        //Record data into database
        returnValue = this.saveUser(user);
        //Return the user profile

        return returnValue;
    }

    @Override
    public UserDTO getUser(String id) {
        UserDTO returnValue = null;
        try {
            this.database.openConnection();
            returnValue = this.database.getUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
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

    private UserDTO saveUser(UserDTO user) {
        UserDTO returnValue = null;
        //Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }
}
