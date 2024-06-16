package org.base.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "")
public class GalaxyLot {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "标单ID")
    private String lotId;
    @ApiModelProperty(value = "标单状态")
    private String status;
    @ApiModelProperty(value = "美金总价")
    private BigDecimal bestBidUds;

    @ApiModelProperty(value = "title")
    private String title;
    @ApiModelProperty(value = "供应商id")
    private long supplierId;

    @ApiModelProperty("结束时间")
    private long closeDate;

    @ApiModelProperty("标单截至时间")
    private String endDate;
}
