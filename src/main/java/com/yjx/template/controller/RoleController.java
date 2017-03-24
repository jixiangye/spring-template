package com.yjx.template.controller;

import com.yjx.template.base.FailResult;
import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import com.yjx.template.pojo.Permission;
import com.yjx.template.pojo.Role;
import com.yjx.template.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "role/view", method = RequestMethod.GET)
    @RequiresPermissions("role:view")
    public String viewRole() {
        return "role";
    }

    @RequestMapping(value = "role/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:view")
    public Result listRoles() {
        return new SuccessResult(roleService.listRoles());
    }

    @RequestMapping(value = "role/add", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:add")
    public Result addRole(Role role) {
        if (StringUtils.isEmpty(role.getRoleCode())) {
            return new FailResult("角色代码不能为空");
        }
        if (StringUtils.isEmpty(role.getRoleName())) {
            return new FailResult("角色名称不能为空");
        }

        Role existingRole = roleService.getByRoleCode(role.getRoleCode());
        if (existingRole != null) {
            return new FailResult("角色代码已存在");
        }
        roleService.addRole(role);
        return new SuccessResult(null);
    }

    @RequestMapping(value = "role/delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:delete")
    public Result deleteRole(Role role) {
        if (StringUtils.isEmpty(role.getRoleCode())) {
            return new FailResult("角色代码不能为空");
        }
        roleService.deleteRole(role);
        return new SuccessResult(null);
    }

    @RequestMapping(value = "role/permission/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:permission")
    public Result rolePermissionList(@RequestParam String roleCode) {
        List<Permission> permissionList = roleService.listPermisionsByRole(roleCode);
        return new SuccessResult(permissionList);
    }

    @RequestMapping(value = "permission/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("role:permission")
    public Result listAllPermissions() {
        List<Permission> permissionList = roleService.listAllPermissions();
        return new SuccessResult(permissionList);
    }
}
