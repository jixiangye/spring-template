package com.yjx.template.base;

import com.alibaba.fastjson.JSON;
import com.yjx.template.dao.UserMapper;
import com.yjx.template.util.HttpUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by yejx on 2017/3/21.
 */
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (HttpUtil.isAjax((HttpServletRequest) request)) {
            Session session = subject.getSession();
            String username = (String) subject.getPrincipal();
            session.setAttribute("user", userMapper.getByUsername(username));
            PrintWriter out = httpServletResponse.getWriter();
            out.println(JSON.toJSONString(new SuccessResult(null)));
            out.flush();
            out.close();
            return false;
        }
        return super.onLoginSuccess(token, subject, request, response);
    }
}
