package com.yjx.template.dao;

import com.yjx.template.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleCode);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleCode);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}