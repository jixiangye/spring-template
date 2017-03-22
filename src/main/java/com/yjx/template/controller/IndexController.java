package com.yjx.template.controller;

import com.yjx.template.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "info", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:view")
    public User info() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute("user");
    }
}
