package org.base.mybatis.controller.params;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.base.mybatis.entity.PageEntity;

import java.io.Serializable;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
@Data
@ApiModel(value = "lot 查询", description = "")
public class LotsQuery extends PageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(" 模糊查询条件")
    private String keywords;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("标单ID")
    private String lotId;
    @ApiModelProperty("开始时间")
    private long startDate;
    @ApiModelProperty("结束时间")
    private long endDate;


}
