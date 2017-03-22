package com.yjx.template.dao;

import com.yjx.template.pojo.RolePermissions;

import java.util.List;

public interface RolePermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissions record);

    int insertSelective(RolePermissions record);

    RolePermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermissions record);

    int updateByPrimaryKey(RolePermissions record);

    List<RolePermissions> getPermissionsByRole(String roleCode);
}