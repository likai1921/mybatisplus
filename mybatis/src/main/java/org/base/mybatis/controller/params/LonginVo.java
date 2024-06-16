package org.base.mybatis.controller.params;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */
@Data
@ApiModel("登录")
public class LonginVo implements Serializable {

    @ApiModelProperty("用户名")
    private String loginName;
    @ApiModelProperty("密码")
    private String pwd;
}
