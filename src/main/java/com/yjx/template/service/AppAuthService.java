package com.yjx.template.service;

import com.yjx.template.pojo.AppAuth;

import java.util.List;

public interface AppAuthService {
    List<AppAuth> listAll();

    AppAuth getById(String appId);
}
