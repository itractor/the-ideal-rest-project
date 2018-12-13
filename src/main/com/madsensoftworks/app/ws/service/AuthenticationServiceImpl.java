package com.madsensoftworks.app.ws.service;

import com.madsensoftworks.app.ws.exceptions.AuthenticationException;
import com.madsensoftworks.app.ws.io.dao.DAO;
import com.madsensoftworks.app.ws.io.dao.implementation.MySQLDAO;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;
import com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;
import com.madsensoftworks.app.ws.utils.UserProfileUtils;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    DAO database;

    @Override
    public UserDTO authenticate(String userName, String password) throws AuthenticationException {
        UserService userService = new UserServiceImpl();
        UserDTO storedUser = userService.getUserByUserName(userName);
        if (storedUser == null) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        String encryptedPassword = null;
        encryptedPassword = new UserProfileUtils().
            generateSecurePassword(password, storedUser.getSalt());

        boolean authenticated = false;
        if(encryptedPassword != null && encryptedPassword.equalsIgnoreCase(storedUser.getEncryptedPassword())){
            if (userName != null && userName.equalsIgnoreCase(storedUser.getEmail())) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        return storedUser;

    }

    @Override
    public String issueAccessToken(UserDTO userProfile) throws AuthenticationException {
        String returnValue = null;

        String newSaltAsPostFix = userProfile.getSalt();
        String accessTokenMaterial = userProfile.getPublicUserId() + newSaltAsPostFix;

        byte[] encryptedAccessToken = null;
        try {
            encryptedAccessToken = new UserProfileUtils().encrypt(userProfile.getEncryptedPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Failed to issue secure acces token");
        }

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        //Split token into equal parts
        int tokenLength = encryptedAccessTokenBase64Encoded.length();

        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength/2);
        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength/2, tokenLength);

        userProfile.setToken(tokenToSaveToDatabase);

        updateUserProfile(userProfile);

        return returnValue;
    }

    @Override
    public void resetSecurityCredentials(String userPassword, UserDTO userProfile) {
        //Generate new salt
        UserProfileUtils userProfileUtils = new UserProfileUtils();
        String salt = userProfileUtils.getSalt(30);

        //Generate new password
        String securePassword = userProfileUtils.generateSecurePassword(userPassword, salt);
        userProfile.setSalt(salt);
        userProfile.setEncryptedPassword(securePassword);

        // Update user profile
        updateUserProfile(userProfile);
    }

    private void updateUserProfile(UserDTO userProfile) {
        this.database = new MySQLDAO();
        try {
            database.openConnection();
            database.updateUserProfile(userProfile);
        } finally {
            database.closeConnection();
        }
    }

}
