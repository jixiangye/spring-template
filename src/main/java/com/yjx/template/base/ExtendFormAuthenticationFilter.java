package com.yjx.template.base;

import com.alibaba.fastjson.JSON;
import com.yjx.template.dao.UserMapper;
import com.yjx.template.util.HttpUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * Created by yejx on 2017/3/21.
 */
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(ExtendFormAuthenticationFilter.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        session.setAttribute("user", userMapper.getByUsername(username));

        if (HttpUtil.isAjax((HttpServletRequest) request)) {
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(new SuccessResult(null)));
            out.flush();
            out.close();
            return false;
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            String requestURI = ((HttpServletRequest) request).getRequestURI();
            if (requestURI.endsWith(".json")) {
                PrintWriter out = response.getWriter();
                out.println(JSON.toJSONString(new FailResult("No Access", "No Access")));
                out.flush();
                out.close();
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }
}
