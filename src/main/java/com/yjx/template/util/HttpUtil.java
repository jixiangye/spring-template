package com.yjx.template.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yejx on 2017/3/21.
 */
public class HttpUtil {
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
