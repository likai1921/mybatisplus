package org.base.mybatis.interceptor;

import com.alibaba.fastjson.JSON;
import org.base.mybatis.interceptor.exception.BizException;
import org.base.mybatis.utils.response.HttpCode;
import org.base.mybatis.utils.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 * 处理登录请求
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final String token = "Bearer";
    private final String tokenHeader = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestHeader = request.getHeader(this.tokenHeader);
        System.out.println(requestHeader);
        if (null != tokenHeader && requestHeader.startsWith(token)) {
            String bearer = requestHeader.replace(token, "").trim();
            System.out.println(bearer);
            if (1 == 1) {
                throw new BizException(401, "token无效");
            }
            return false;
        } else {
            return true;
        }
    }

    private void handleFalseResponse(HttpServletResponse response)
            throws Exception {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(HttpResponse.failed(401, "token无效")));
        response.getWriter().flush();
    }
}
