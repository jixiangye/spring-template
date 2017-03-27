package com.yjx.template.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class AssignPermissionDTO implements Serializable {
    private static final long serialVersionUID = -6601458938714093696L;

    private String roleCode;

    @JSONField(name = "permissionCode")
    private List<String> permissionCodeList;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<String> getPermissionCodeList() {
        return permissionCodeList;
    }

    public void setPermissionCodeList(List<String> permissionCodeList) {
        this.permissionCodeList = permissionCodeList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssignPermissionDTO{");
        sb.append("roleCode='").append(roleCode).append('\'');
        sb.append(", permissionCodeList=").append(permissionCodeList);
        sb.append('}');
        return sb.toString();
    }
}
