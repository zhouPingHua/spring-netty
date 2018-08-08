package com.zph.cralwer.netty.annotation;

import java.lang.annotation.*;

/**
 * @author zph  on 2018/8/6
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMethod {
    String value();
}
