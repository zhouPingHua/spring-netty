package com.zph.netty.executor;

import com.zph.netty.protocol.RpcRequest;
import com.zph.netty.protocol.RpcResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author zph  on 2018/8/8
 */
public class MessageRecvInitializeTask implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRecvInitializeTask.class);

    private Map<String, Object> handlerMap;

    private ChannelHandlerContext ctx;

    private RpcRequest request;

    public MessageRecvInitializeTask(ChannelHandlerContext ctx,RpcRequest request,Map<String, Object> handlerMap){
        this.ctx = ctx;
        this.request = request;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        LOGGER.info("Receive request " + request.getRequestId());
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t.toString());
            LOGGER.error("RPC Server handle request error",t);
        }
        ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                LOGGER.info("Send response for request " + request.getRequestId());
            }
        });
    }


    private Object handle(RpcRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        LOGGER.info(serviceClass.getName());
        LOGGER.info(methodName);
        for (int i = 0; i < parameterTypes.length; ++i) {
            LOGGER.info(parameterTypes[i].getName());
        }
        for (int i = 0; i < parameters.length; ++i) {
            LOGGER.info(parameters[i].toString());
        }

        // JDK reflect
        /*Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);*/

        // Cglib reflect
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }
}
