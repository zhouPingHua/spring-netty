package com.zph.netty.server;

import com.zph.netty.executor.MessageRecvExecutor;
import com.zph.netty.executor.MessageRecvInitializeTask;
import com.zph.netty.protocol.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author zph  on 2018/8/8
 */
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {


    private static final Logger LOGGER = LoggerFactory.getLogger(RpcHandler.class);

    private Map<String, Object> handlerMap;

    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
        MessageRecvInitializeTask task = new MessageRecvInitializeTask(ctx,request,handlerMap);
        MessageRecvExecutor.submit(task);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("server caught exception", cause);
        ctx.close();
    }

}
