package org.base.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaowenwen
 * @date 2021/1/14
 */

@ApiModel("重写IPage返回数据")
@Data
public class IPageResult<T> implements Serializable {

    private List<T> list;
    private long pageSize;
    private long pageNum;
    private long total;


}
