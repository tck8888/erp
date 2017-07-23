package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.User;

import com.tck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by admin on 2017/7/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public String upload() {
        return "/login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public BaseData<User> login(@RequestParam("username") String username, @RequestParam("password") String password) {

        return userService.login(username, password);
    }

    @RequestMapping("/register")
    public BaseData<String> register(@RequestParam("username") java.lang.String username, @RequestParam("password") java.lang.String password) {
        return userService.register(username, password);
    }

}
