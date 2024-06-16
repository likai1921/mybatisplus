package org.base.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author xuzf
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    private String updateName;

    private String updateDate;

    private Long updateBy;

    private String remarks;

    private Integer delFlag;

    private String locked;


}
