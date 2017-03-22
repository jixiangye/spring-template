package com.yjx.template.dao;

import com.yjx.template.pojo.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permissionCode);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String permissionCode);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}