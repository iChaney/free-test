package com.qiang.annotations;

import java.lang.annotation.*;

/**
 * @author liq
 * @date 2022/1/11 16:26
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiRequestBody {
}
