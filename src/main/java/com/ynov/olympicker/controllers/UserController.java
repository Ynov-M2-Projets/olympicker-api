package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.ChangePasswordDTO;
import com.ynov.olympicker.dto.UpdateUserDTO;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/change_password", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(ChangePasswordDTO changePasswordDTO, Principal principal) {
        User user = authService.whoami(principal);
        boolean changed = this.userService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword(), user);
        if (!changed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong old password");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User updateUser(UpdateUserDTO userDTO, Principal principal) {
        User user = authService.whoami(principal);
        return this.userService.updateUser(userDTO, user);
    }
}
