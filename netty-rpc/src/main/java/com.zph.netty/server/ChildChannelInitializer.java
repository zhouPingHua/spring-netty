package com.zph.netty.server;

import com.zph.netty.core.MessageSendChannelInitializer;
import com.zph.netty.protocol.RpcDecoder;
import com.zph.netty.protocol.RpcEncoder;
import com.zph.netty.protocol.RpcRequest;
import com.zph.netty.protocol.RpcResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zph  on 2018/8/8
 */
public class ChildChannelInitializer extends ChannelInitializer {

    private Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    public ChildChannelInitializer(Map<String, Object> handlerMap){
        this.handlerMap = handlerMap;
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
//                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MessageSendChannelInitializer.MESSAGE_LENGTH, 0, MessageSendChannelInitializer.MESSAGE_LENGTH))
                .addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0))
                .addLast(new RpcDecoder(RpcRequest.class))
                .addLast(new RpcEncoder(RpcResponse.class))
                .addLast(new RpcHandler(handlerMap));
    }
}
