package com.madsensoftworks.app.ws.ui.entrypoints;

import com.madsensoftworks.app.ws.service.UserServiceImpl;
import com.madsensoftworks.app.ws.service.UserService;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;
import com.madsensoftworks.app.ws.ui.model.request.CreateUserRequestModel;
import com.madsensoftworks.app.ws.ui.model.response.UserProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest createUser(CreateUserRequestModel requestObject) {
        UserProfileRest returnValue = new UserProfileRest();

        //Prepare userDTO
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(requestObject,userDto);

        //Create new user
        UserService userService = new UserServiceImpl();
        UserDTO createdUserProfile = userService.createUser(userDto);

        //Prepare response
        BeanUtils.copyProperties(createdUserProfile, returnValue);
        return returnValue;

    }

}
