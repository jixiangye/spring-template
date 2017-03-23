package com.yjx.template.service;

import com.yjx.template.pojo.Role;

import java.util.List;

/**
 * Created by yejx on 2017/3/23.
 */
public interface RoleService {
    int addRole(Role role);

    int deleteRole(Role role);

    Role getByRoleCode(String roleCode);

    List<Role> listRoles();
}
