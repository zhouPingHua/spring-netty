package com.zph.cralwer.processor;

import java.util.Map;

/**
 * @author zph  on 2018/7/26
 */
public interface ProcessorRegister {

    /**
     * 注册处理器
     * @param domain
     * @param method
     * @param processor
     * @return
     */
    Map<String,PageProcessor> regist(String domain, String method, PageProcessor processor);

    /**
     * 获取处理器
     * @param domain
     * @return
     */
    Map<String,PageProcessor> load(String domain);

    /**
     * 获取bean工厂，bean工厂中的服务可以被处理器通过@Resource注解直接注入
     * @return
     */
    ServiceFactory getServiceFactory();
}
