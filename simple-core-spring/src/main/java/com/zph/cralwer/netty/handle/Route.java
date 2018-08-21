package com.zph.cralwer.netty.handle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zph  on 2018/8/6
 */
@Data
@Slf4j
public class Route {

    private Map<String, Method> methods = new HashMap<String, Method>();

    private String uri;

    private Object clazz;


    public Object invoke(String methodName, Object... args) {
        long start = System.currentTimeMillis();
        try {
            Method method = methods.get(methodName);
            return method.invoke(clazz, args);
        } catch (IllegalAccessException e) {
            log.error("调用了未授权的private方法或参数非法");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error("controller层方法调用错误");
            e.printStackTrace();
        }
        log.info("远程调用耗时:"+(System.currentTimeMillis() - start));
        return "";
    }
}
