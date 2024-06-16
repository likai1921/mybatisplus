package org.base.mybatis.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaowenwen
 * @date 2021/1/21
 * 拦截器 自定义逻辑处理
 */
@Component
@Slf4j
public class WebInterceptorHandel implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求的路径为: " + request.getRequestURI() + ", 请求的参数为：" + JSON.toJSONString(request.getParameterMap()));
        return true;
    }
}
