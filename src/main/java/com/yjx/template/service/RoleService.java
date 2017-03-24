package com.yjx.template.service;

import com.yjx.template.pojo.Permission;
import com.yjx.template.pojo.Role;

import java.util.List;

public interface RoleService {
    int addRole(Role role);

    int deleteRole(Role role);

    Role getByRoleCode(String roleCode);

    List<Role> listRoles();

    List<Permission> listPermisionsByRole(String roleCode);

    List<Permission> listAllPermissions();
}
