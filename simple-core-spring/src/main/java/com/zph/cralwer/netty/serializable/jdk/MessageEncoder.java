package com.zph.cralwer.netty.serializable.jdk;

import com.zph.cralwer.netty.model.Message;
import com.zph.cralwer.netty.utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @author zph  on 2018/8/6
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        byte[] datas = ByteUtils.objectToByte(message);
        out.writeBytes(datas);
        ctx.flush();
    }
}
