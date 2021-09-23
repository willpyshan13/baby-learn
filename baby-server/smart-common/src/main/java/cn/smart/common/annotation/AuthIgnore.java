package cn.smart.common.annotation;

import java.lang.annotation.*;

/**
 * api 接口忽略 Token 验证
 * @author Ye
 * @Date 2018-3-27 17:43:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
