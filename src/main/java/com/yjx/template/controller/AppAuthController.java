package com.yjx.template.controller;

import com.yjx.template.pojo.AppAuth;
import com.yjx.template.service.AppAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppAuthController {
    @Autowired
    private AppAuthService appAuthService;

    @RequestMapping("/listAppAuth")
    @ResponseBody
    public List<AppAuth> listAll(){
        return appAuthService.listAll();
    }

    @RequestMapping("/getById")
    @ResponseBody
    public AppAuth getById(@RequestParam String appId){
        return appAuthService.getById(appId);
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
