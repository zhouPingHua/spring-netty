package com.zph.netty.configuration;

import com.zph.netty.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zph  on 2018/8/8
 */
@Configuration
public class BeanConfiguration {

    private static Logger logger = LoggerFactory.getLogger(BeanConfiguration.class);

    @Value("${server.netty.address}")
    private String serverAddress;

    @Value("${registry.address}")
    private String serviceRegistry;


    @Bean
    public RpcServer rpcServer() {
        RpcServer rpcServer = new RpcServer(serverAddress,serviceRegistry);
        return rpcServer;
    }

}
