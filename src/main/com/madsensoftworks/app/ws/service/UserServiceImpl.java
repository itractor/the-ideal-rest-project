package main.com.madsensoftworks.app.ws.service;

import main.com.madsensoftworks.app.ws.shared.dto.UserDTO;
import main.com.madsensoftworks.app.ws.utils.UserProfileUtils;

public class UserServiceImpl implements UserService{

    UserProfileUtils userProfileUtils = new UserProfileUtils();

    @Override
    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = new UserDTO();

        //Validate required fields
        userProfileUtils.validateRequiredFields(user);
        //Check if user exists

        //Create entity object

        //Generate secure public user id
        //Generate salt

        //Generate secure password

        //Record data into database

        //Return the user profile

        return returnValue;
    }
}
