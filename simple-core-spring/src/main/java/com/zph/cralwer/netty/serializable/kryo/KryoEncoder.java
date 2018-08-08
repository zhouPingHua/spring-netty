package com.zph.cralwer.netty.serializable.kryo;

import com.zph.cralwer.netty.model.Message;
import com.zph.cralwer.netty.serializable.kryo.factory.KryoSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zph  on 2018/8/6
 */
public class KryoEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        KryoSerializer.serialize(message, out);
        ctx.flush();
    }
}
