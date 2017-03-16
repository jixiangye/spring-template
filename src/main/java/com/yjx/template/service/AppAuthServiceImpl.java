package com.yjx.template.service;

import com.yjx.template.dao.AppAuthMapper;
import com.yjx.template.pojo.AppAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppAuthServiceImpl implements AppAuthService {
    @Autowired
    private AppAuthMapper appAuthMapper;

    public List<AppAuth> listAll() {
        return appAuthMapper.listAll();
    }

    public AppAuth getById(String appId) {
        return appAuthMapper.selectByPrimaryKey(appId);
    }
}
