package com.yjx.template.dao;

import com.yjx.template.pojo.AppAuth;

import java.util.List;

public interface AppAuthMapper {
    int deleteByPrimaryKey(String appId);

    int insert(AppAuth record);

    int insertSelective(AppAuth record);

    AppAuth selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(AppAuth record);

    int updateByPrimaryKey(AppAuth record);

    List<AppAuth> listAll();
}