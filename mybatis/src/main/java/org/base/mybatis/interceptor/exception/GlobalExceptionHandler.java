package org.base.mybatis.interceptor.exception;

import lombok.extern.slf4j.Slf4j;
import org.base.mybatis.utils.response.HttpCode;
import org.base.mybatis.utils.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    ;

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public HttpResponse bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResponse.failed(e.getCode(), e.getMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public HttpResponse exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return HttpResponse.failed(HttpCode.body_not_match.getCode(), HttpCode.body_not_match.getMsg());
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResponse exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return HttpResponse.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
