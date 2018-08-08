package com.zph.cralwer.netty.serializable.kryo;

import com.zph.cralwer.netty.serializable.kryo.factory.KryoSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zph  on 2018/8/6
 */
public class KryoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object obj = KryoSerializer.deserialize(in);
        out.add(obj);
    }
}