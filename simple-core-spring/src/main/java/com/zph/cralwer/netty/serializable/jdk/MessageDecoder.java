package com.zph.cralwer.netty.serializable.jdk;

import com.zph.cralwer.netty.utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zph  on 2018/8/6
 */
public class MessageDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object obj = ByteUtils.byteToObject(new byte[byteBuf.readableBytes()]);
        list.add(obj);
    }
}
