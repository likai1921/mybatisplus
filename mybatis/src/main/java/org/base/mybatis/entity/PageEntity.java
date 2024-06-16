package org.base.mybatis.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
@Data
@ApiModel("分页参数")
public class PageEntity implements Serializable {
    @ApiModelProperty(value = "每页条数")
    private int pageSize = 50;
    @ApiModelProperty("页")
    private int pageNum = 1;
}
