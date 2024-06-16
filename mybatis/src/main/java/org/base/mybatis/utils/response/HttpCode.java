package org.base.mybatis.utils.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhaowenwen
 * @date 2021/1/18
 */

public enum HttpCode implements Serializable {

    /**
     * 成功状态码
     */
    success(0, "success"),
    /**
     * 失败状态码
     */
    failed(-1, "failed"),

    body_not_match(-2, "请求的数据格式不符!");
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Integer code;

    HttpCode(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
