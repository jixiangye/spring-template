package com.yjx.template.service;

import com.yjx.template.pojo.AssignRoleDTO;
import com.yjx.template.pojo.Role;
import com.yjx.template.pojo.User;

import java.util.List;

public interface UserService {
    int register(User user);

    User getByUsername(String username);

    void updatePassword(User user);

    List<User> listUser();

    List<Role> userRoleList(String username);

    void saveUserRole(AssignRoleDTO assignRoleDTO);
}
