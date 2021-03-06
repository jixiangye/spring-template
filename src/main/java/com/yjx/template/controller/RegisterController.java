package com.yjx.template.controller;

import com.yjx.template.base.FailResult;
import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import com.yjx.template.pojo.User;
import com.yjx.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Result doRegister(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return new FailResult("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            return new FailResult("邮箱不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return new FailResult("密码不能为空");
        }

        User existingUser = userService.getByUsername(user.getUsername());
        if (existingUser != null) {
            return new FailResult("该用户名已存在");
        }
        userService.register(user);
        return new SuccessResult(null);
    }
}
