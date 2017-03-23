package com.yjx.template.dao;

import com.yjx.template.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleCode);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleCode);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> listRoles();
}