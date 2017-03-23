package com.yjx.template.controller;

import com.yjx.template.base.FailResult;
import com.yjx.template.base.Result;
import com.yjx.template.base.SuccessResult;
import com.yjx.template.pojo.ResetPasswordDTO;
import com.yjx.template.pojo.User;
import com.yjx.template.service.UserService;
import com.yjx.template.util.EmailUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by yejx on 2017/3/22.
 */
@Controller
public class ForgetController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "forget", method = RequestMethod.GET)
    public String forget() {
        return "forget";
    }

    @RequestMapping(value = "forget/sendCode", method = RequestMethod.POST)
    @ResponseBody
    public Result sendCode(@RequestParam String username) {
        User user = userService.getByUsername(username);
        if (user == null) {
            return new FailResult("", "该用户不存在");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            return new FailResult("", "该用户未绑定邮箱");
        }

        Session session = SecurityUtils.getSubject().getSession();
        String code = UUID.randomUUID().toString().replace("-", "");
        session.setAttribute("reset_code", code);
        EmailUtil.sendEmail(user.getEmail(), "重置验证码为：" + code);
        return new SuccessResult(null);
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (StringUtils.isEmpty(resetPasswordDTO.getUsername())) {
            return new FailResult("用户名不能为空");
        }
        if (StringUtils.isEmpty(resetPasswordDTO.getCode())) {
            return new FailResult("验证码不能为空");
        }
        if (StringUtils.isEmpty(resetPasswordDTO.getPassword())) {
            return new FailResult("密码不能为空");
        }
        if (StringUtils.isEmpty(resetPasswordDTO.getRePassword())) {
            return new FailResult("确认密码不能为空");
        }
        if (!resetPasswordDTO.getPassword().equals(resetPasswordDTO.getRePassword())) {
            return new FailResult("密码和确认密码不一致");
        }

        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute("reset_code");
        if (!resetPasswordDTO.getCode().equals(code)) {
            return new FailResult("验证码错误");
        }

        session.removeAttribute("reset_code");
        User user = new User();
        BeanUtils.copyProperties(resetPasswordDTO, user);
        userService.updatePassword(user);
        return new SuccessResult(null);
    }
}
