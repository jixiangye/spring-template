package com.yjx.template.service;

import com.yjx.template.dao.RoleMapper;
import com.yjx.template.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yejx on 2017/3/23.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

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
}
