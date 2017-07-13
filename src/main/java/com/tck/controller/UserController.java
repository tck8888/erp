package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.User;

import com.tck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by admin on 2017/7/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public BaseData<User> login(@RequestParam("username") String username, @RequestParam("password") String password) {

        return userService.login(username, password);
    }

    @RequestMapping("/register")
    public BaseData<String> register(@RequestParam("username") java.lang.String username, @RequestParam("password") java.lang.String password) {
        return userService.register(username, password);
    }

}
