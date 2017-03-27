package com.yjx.template.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by yejx on 2017/3/27.
 */
public class AssignRoleDTO {
    private String username;

    @JSONField(name = "roleCode")
    private List<String> roleCodeList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoleCodeList() {
        return roleCodeList;
    }

    public void setRoleCodeList(List<String> roleCodeList) {
        this.roleCodeList = roleCodeList;
    }
}
