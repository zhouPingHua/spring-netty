package com.zph.cralwer.processor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zph  on 2018/7/27
 */
public class SpringServiceFactory implements ServiceFactory, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringServiceFactory.applicationContext = applicationContext;
    }

    @Override
    public Object getService(String beanName) {
        try{
            return applicationContext.getBean(beanName);
        }catch (BeansException e){
            return null;
        }
    }

    @Override
    public <T> T getService(Class<T> beanType) {
        try{
            return applicationContext.getBean(beanType);
        }catch (BeansException e){
            return null;
        }
    }


}