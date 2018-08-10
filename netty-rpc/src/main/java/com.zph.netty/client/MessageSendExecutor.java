package com.zph.netty.client;

import com.zph.netty.client.proxy.MessageSendProxy;

import java.lang.reflect.Proxy;

/**
 * @author zph  on 2018/8/9
 */
public class MessageSendExecutor {

    private RpcClient loader = RpcClient.getInstance();

    public MessageSendExecutor(String serverAddress) {
        loader.load(serverAddress);
    }

    public void stop() {
        loader.unLoad();
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface)
        );
    }

}
