package org.base.mybatis.controller;

import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.base.mybatis.controller.params.LonginVo;
import org.base.mybatis.interceptor.exception.BizException;
import org.base.mybatis.utils.response.HttpResponse;
import org.base.mybatis.utils.response.ResponseResult;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "登录接口实现")
@ResponseResult
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public HttpResponse login(@RequestBody LonginVo longinVo) {
        //  Assert.isNull(longinVo, "登录的用户名密码为空！");
        Assert.notNull(longinVo, "登录的用户名密码为空！");
        return HttpResponse.success("token");
    }

    @ApiOperation("推出")
    @PostMapping("/loginOut")
    public HttpResponse loginOut(@RequestBody LonginVo longinVo) {
        //  Assert.isNull(longinVo, "登录的用户名密码为空！");

        return HttpResponse.success();
    }

    @ApiOperation("异常1")
    @PostMapping("/error1")
    public HttpResponse error1(@RequestBody LonginVo longinVo) {
        if (StringUtil.isEmpty(longinVo.getPwd())) {
            throw new BizException(-200, "用户名不能为空");
        }
        return HttpResponse.success("token");
    }

    @ApiOperation("异常2 ")
    @PutMapping("/update")
    public boolean update(@RequestBody LonginVo user) {
        System.out.println("开始更新...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @ApiOperation("异常3 ")
    @DeleteMapping("/delete")
    public boolean delete(@RequestBody LonginVo user) {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }
}
