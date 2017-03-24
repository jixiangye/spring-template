package com.yjx.template.service;

import com.yjx.template.dao.PermissionMapper;
import com.yjx.template.dao.RoleMapper;
import com.yjx.template.dao.RolePermissionsMapper;
import com.yjx.template.pojo.Permission;
import com.yjx.template.pojo.Role;
import com.yjx.template.pojo.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public int addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public int deleteRole(Role role) {
        return roleMapper.deleteByPrimaryKey(role.getRoleCode());
    }

    public Role getByRoleCode(String roleCode) {
        return roleMapper.selectByPrimaryKey(roleCode);
    }

    public List<Role> listRoles() {
        return roleMapper.listRoles();
    }

    public List<Permission> listPermisionsByRole(String roleCode) {
        List<Permission> list = new ArrayList<Permission>();
        List<RolePermissions> rolePermissionsList = rolePermissionsMapper.getPermissionsByRole(roleCode);
        for (RolePermissions rolePermissions : rolePermissionsList) {
            Permission permission = permissionMapper.selectByPrimaryKey(rolePermissions.getPermissionCode());
            list.add(permission);
        }
        return list;
    }

    public List<Permission> listAllPermissions() {
        return permissionMapper.listAllPermissions();
    }
}
