package com.yjx.template.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
