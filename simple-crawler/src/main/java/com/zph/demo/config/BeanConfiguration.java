package com.zph.demo.config;

import com.zph.cralwer.annotation.ProcessScan;
import com.zph.cralwer.processor.ProcessorRegister;
import com.zph.cralwer.processor.ServiceFactory;
import com.zph.cralwer.processor.SpringProcessorRegister;
import com.zph.cralwer.processor.SpringServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

/**
 * bean配置
 * @author zph  on 2018/7/27
 */
@Configuration
@ProcessScan("com.zph")
public class BeanConfiguration {

    private static Logger logger = LoggerFactory.getLogger(BeanConfiguration.class);





    /**
     * SpringServiceFactory 用作在spring环境下，会将所有spring bean 当做服务
     * SimpleServiceFactory 用作在非spring环境下 ，服务需要手动初始
     *
     * @return
     */
    @Bean
    public ServiceFactory serviceFactory() {
        ServiceFactory serviceFactory = new SpringServiceFactory();
        return serviceFactory;
    }

    /**
     * 业务逻辑处理器注册中心
     * SpringProcessorRegister：spring环境下的自动扫描注册中心
     * SimpleProcessorRegister：基于自定义注解自动扫描处理器的注册中心
     *
     * @return
     */
    @Profile(value = {"dev"})
    @Bean
    public ProcessorRegister processorRegisterDev(ServiceFactory serviceFactory) {
        ProcessorRegister processorRegister = new SpringProcessorRegister();
        return processorRegister;
    }

    @Profile(value = {"test", "prod"})
    @Bean
    public ProcessorRegister processorRegister(ServiceFactory serviceFactory) {
        ProcessorRegister processorRegister = new SpringProcessorRegister();
        return processorRegister;
    }


    /**
     * 爬虫实例
     *
     * @param processorRegister
     * @return
     */
    @Bean
    @Scope("prototype")
    public CarrierCrawler crawler(ProcessorRegister processorRegister) {
        CarrierCrawler crawler = new CarrierCrawler();
        crawler.setProcessorRegister(processorRegister);
        return crawler;
    }
}

    