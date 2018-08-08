package com.zph.cralwer.processor;

/**
 * @author zph  on 2018/7/26
 */
public interface ServiceFactory {

    Object getService(String beanName);

    <T> T getService(Class<T> beanType);

}
