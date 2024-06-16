package org.base.mybatis.interceptor.exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.base.mybatis.utils.response.HttpCode;
import org.base.mybatis.utils.response.HttpResponse;

import java.sql.Timestamp;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Timestamp timestamp;

    public BizException() {
        super();
    }

    public BizException(HttpResponse errorInfoInterface) {
        super(String.valueOf(errorInfoInterface.getCode()));
        this.code = errorInfoInterface.getCode();
        this.msg = errorInfoInterface.getMsg();
    }

    public BizException(HttpResponse errorInfoInterface, Throwable cause) {
        super(String.valueOf(errorInfoInterface.getCode()), cause);
        this.code = errorInfoInterface.getCode();
        this.msg = errorInfoInterface.getMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.msg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.code = errorCode;
        this.msg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.code = errorCode;
        this.msg = errorMsg;
    }

}
