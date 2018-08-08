package com.zph.cralwer.processor;

import com.zph.cralwer.annotation.Process;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 处理器注册中心
 * 扫描项目中所有处理器
 * @author zph  on 2018/7/27
 */
public class SimpleProcessorRegister extends AbstractProcessorRegister {

    private static Logger logger = LoggerFactory.getLogger(SimpleProcessorRegister.class);

    private ServiceFactory serviceFactory;

    public SimpleProcessorRegister(String packagePrefix, ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        init(packagePrefix);
    }

    @Override
    public ServiceFactory getServiceFactory() {
        return this.serviceFactory;
    }

    /**
     * 初始化处理器工厂 并注入服务
     * @param packagePrefix
     */
    private void init(String packagePrefix){
        Reflections reflections = new Reflections(packagePrefix);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Process.class);
        for (Class<?> clazz : classes){
            try {
                Object instance = clazz.newInstance();
                if (instance instanceof PageProcessor) {
                    Process annotation = clazz.getAnnotation(Process.class);
                    doInject(instance);
                    for (String domain: annotation.domain()) {
                        regist(domain, annotation.method(), (PageProcessor) instance);
                    }
                }
            } catch (InstantiationException e) {
                logger.error(e.getMessage(),e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}

    