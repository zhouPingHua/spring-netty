package com.zph.cralwer.netty.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zph  on 2018/7/26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface Spider {

    String value();
}
