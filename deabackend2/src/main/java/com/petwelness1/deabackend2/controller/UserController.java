package com.petwelness1.deabackend2.controller;


import com.petwelness1.deabackend2.entity.User;
import com.petwelness1.deabackend2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/register-new-user"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @PostConstruct
    public void initRoleAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping({"/for-admin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "this url is only accessible to admin";
    }

    @GetMapping({"/for-user"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "this url is only accessible to user";
    }
}
