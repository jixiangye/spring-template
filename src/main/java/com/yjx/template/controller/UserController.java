package com.yjx.template.controller;

import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import com.yjx.template.pojo.AssignRoleDTO;
import com.yjx.template.service.RoleService;
import com.yjx.template.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yejx on 2017/3/23.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    @RequiresPermissions("user:list")
    public String userPage() {
        return "user";
    }

    @RequestMapping(value = "user/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:list")
    public Result listUser() {
        return new SuccessResult(userService.listUser());
    }

    @RequestMapping(value = "user/role/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:addRole")
    public Result userRoleList(@RequestParam String username) {
        return new SuccessResult(userService.userRoleList(username));
    }


    @RequestMapping(value = "user/role/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:addRole")
    public Result userRoleSave(@RequestBody AssignRoleDTO assignRoleDTO) {
        userService.saveUserRole(assignRoleDTO);
        return new SuccessResult(null);
    }
}
