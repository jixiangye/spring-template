package com.yjx.template.controller;

import com.yjx.template.pojo.User;
import com.yjx.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(User user){
        userService.register(user);
        return "login";
    }
}
