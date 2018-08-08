package com.zph.cralwer.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zph  on 2018/7/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ProcessScannerRegistrar.class)
public @interface ProcessScan {

    /**
     * 要扫描的包名 如：com.youyu
     * @return
     */
    String value() default "";
}
