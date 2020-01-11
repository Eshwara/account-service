package com.eshwar.account.controller;

import com.eshwar.account.model.User;
import com.eshwar.account.response.UserResponse;
import com.eshwar.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/")
public class UserConroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse createUser(@RequestBody User user){

         return userService.createUser(user);

    }

    @RequestMapping(value = "signin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse signin(@RequestBody User user){

        return userService.sigin(user);

    }

    @RequestMapping(method =  RequestMethod.GET, value = "authenticate/user/{token}")
    public UserResponse findUserByToken(@PathVariable("token") String token){

        return userService.findUserbyToken(token);
    }
}
