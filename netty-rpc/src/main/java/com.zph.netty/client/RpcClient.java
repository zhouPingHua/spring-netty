package com.zph.netty.client;

import com.zph.netty.client.proxy.MessageSendProxy;
import com.zph.netty.registry.ServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * @author zph  on 2018/8/9
 */
public class RpcClient {

    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    public RpcClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface)
        );
    }

    public void stop() {
        serviceDiscovery.stop();
        ConnectManage.getInstance().stop();
    }

}
