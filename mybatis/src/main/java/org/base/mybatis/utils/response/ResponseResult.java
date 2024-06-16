package org.base.mybatis.utils.response;

import java.lang.annotation.*;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
