package com.yjx.template.base;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BizExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            Result result;
            if (ex instanceof BizException) {
                BizException bizException = (BizException) ex;
                result = new FailResult(bizException.getCode(), bizException.getMessage());
            } else {
                result = new FailResult("", ex.getMessage());
            }
            writer.write(JSON.toJSONString(result));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
