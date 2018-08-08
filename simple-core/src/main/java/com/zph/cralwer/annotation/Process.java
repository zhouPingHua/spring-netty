package com.zph.cralwer.annotation;

import java.lang.annotation.*;

/**
 * @author zph  on 2018/7/26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Process {

    /**
     * 方法名
     * @return
     */
    String method();

    /**
     * 目标
     * @return
     */
    String [] domain();
}
