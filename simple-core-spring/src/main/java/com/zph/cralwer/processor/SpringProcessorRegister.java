package com.zph.cralwer.processor;

import com.zph.cralwer.annotation.Process;
import com.zph.cralwer.netty.NettyHttpServer;
import com.zph.cralwer.netty.annotation.RequestMethod;
import com.zph.cralwer.netty.annotation.Spider;
import com.zph.cralwer.netty.handle.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zph  on 2018/7/27
 */
@Component
public class SpringProcessorRegister extends AbstractProcessorRegister implements ApplicationContextAware {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static Map<String, Route> routeHashMap = new HashMap<String, Route>();

    private static ApplicationContext applicationContext;

    @PostConstruct
    public void preStart() throws IOException {
        //初始化请求路由
        initRoute();
        //启动netty服务
        NettyStart();
    }

    @Override
    public ServiceFactory getServiceFactory() {
        return null;
    }


    private void initProcessRepository(){
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(Process.class);
        if (serviceBeanMap!=null && serviceBeanMap.size()>0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                if (serviceBean instanceof PageProcessor){
                    Process annotation = serviceBean.getClass().getAnnotation(Process.class);
                    for (String domain: annotation.domain()) {
                        regist(domain, annotation.method(), (PageProcessor) serviceBean);
                    }
                }
            }
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringProcessorRegister.applicationContext = applicationContext;
        initProcessRepository();
    }

    private void NettyStart() {
        new NettyHttpServer().start();
    }

    private void initRoute() {
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(Spider.class);
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            Route route = new Route();
            route.setUri(beanName);
            route.setClazz(bean);
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestMethod requestMethod = method.getAnnotation(RequestMethod.class);
                if (requestMethod == null) {
                    continue;
                }
                route.getMethods().put(requestMethod.value(), method);
            }
            routeHashMap.put(route.getUri(), route);
        }
    }

}
