package org.base.mybatis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.base.mybatis.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaowenwen
 * @date 2021/1/21
 */
@RestController
@Api(tags = "拦截器测试类")
@RequestMapping("/path")
public class TestController {

    @GetMapping("/test1")
    @ApiModelProperty("你好测试")
    public HttpResponse getTest() {
        return HttpResponse.success("hello ，test");
    }
}
