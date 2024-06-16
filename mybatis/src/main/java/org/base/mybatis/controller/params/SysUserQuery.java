package org.base.mybatis.controller.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaowenwen
 * @date 2021/1/13
 */
@Data
@ApiModel(value = "SysUser对象 查询", description = "")
public class SysUserQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "模糊条件，电话号码")
    private String keywords;
    @ApiModelProperty(value = "登录名")
    private String loginName;

}
