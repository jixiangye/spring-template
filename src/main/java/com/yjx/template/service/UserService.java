package com.yjx.template.service;

import com.yjx.template.pojo.User;

public interface UserService {
    int register(User user);

    User getByUsername(String username);

    void updatePassword(User user);
}
