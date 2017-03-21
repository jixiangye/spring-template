package com.yjx.template.controller;

import com.yjx.template.base.BizException;
import com.yjx.template.base.FailResult;
import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(StringUtils.isEmpty(username)){
            throw new BizException("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new BizException("密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return new FailResult("", "该用户不存在");
            }
            return new FailResult("", "用户名或密码错误");
        }
        return new SuccessResult(null);
    }
}
