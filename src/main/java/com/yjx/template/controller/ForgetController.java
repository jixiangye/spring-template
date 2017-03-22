package com.yjx.template.controller;

import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yejx on 2017/3/22.
 */
@Controller
public class ForgetController {
    @RequestMapping(value = "forget", method = RequestMethod.GET)
    public String forget(){
        return "forget";
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(){

        return new SuccessResult(null);
    }
}
