package org.base.mybatis.utils.response;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zhaowenwen
 * @date 2021/1/18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;
    private Timestamp timestamp;

    public HttpResponse() {
        this.code = HttpCode.success.getCode();
        this.msg = HttpCode.success.getMsg();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public HttpResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public static HttpResponse createT(Integer code, String msg, Object data) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setCode(code);
        httpResponse.setData(data);
        httpResponse.setMsg(msg);
        return httpResponse;
    }

    public static HttpResponse success() {

        return createT(HttpCode.success.getCode(), HttpCode.success.getMsg(), null);
    }

    public static HttpResponse success(Object data) {

        return createT(HttpCode.success.getCode(), HttpCode.success.getMsg(), data);
    }


    public static HttpResponse failed() {

        return createT(HttpCode.failed.getCode(), HttpCode.failed.getMsg(), null);
    }

    public static HttpResponse failed(String msg) {
        if (StringUtils.isBlank(msg)) {
            msg = HttpCode.failed.getMsg();
        }
        return createT(HttpCode.failed.getCode(), msg, null);
    }

    public static HttpResponse failed(Integer code, String msg) {
        if (StringUtils.isBlank(msg)) {
            msg = HttpCode.failed.getMsg();
        }
        return createT(code, msg, null);
    }

    @Data
    public static class Pages {
        private long total;
        private long pageSize;
        private long pageNum;
        private Object data;

        public Pages(long total, long pageSize, long pageNum, Object data) {
            this.data = data;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
        }
    }
}
