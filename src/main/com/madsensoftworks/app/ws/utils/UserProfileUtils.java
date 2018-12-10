package main.com.madsensoftworks.app.ws.utils;

import main.com.madsensoftworks.app.ws.exceptions.MissingRequiredFieldException;
import main.com.madsensoftworks.app.ws.shared.dto.UserDTO;
import main.com.madsensoftworks.app.ws.ui.model.response.ErrorMessages;

public class UserProfileUtils {

    public void validateRequiredFields(UserDTO userDto) throws MissingRequiredFieldException {
        if (userDto.getFirstName() == null ||
                userDto.getFirstName().isEmpty() ||
                userDto.getLastName() == null ||
                userDto.getLastName().isEmpty() ||
                userDto.getEmail() == null ||
                userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null ||
                userDto.getPassword().isEmpty()){
            throw new MissingRequiredFieldException(
                    ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }

}
