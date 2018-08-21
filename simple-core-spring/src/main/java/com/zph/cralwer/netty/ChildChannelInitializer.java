package com.zph.cralwer.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author zph  on 2018/8/6
 */
public class ChildChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast("respDecoder-reqEncoder", new HttpServerCodec())
                //把多个消息转化成一个消息(FullHttpRequest或者FullHttpResponse),原因是HTTP解码器在每个HTTP消息中会生成多个消息对象
                //tcp协议三次握手，每次http请求都会有两次消息过来。
                .addLast("http-aggregator", new HttpObjectAggregator(65536))
                //支持处理异步发送大数据文件，但不占用过多的内存，防止发生内存泄漏。
                .addLast(new ChunkedWriteHandler())
                .addLast("action-handler", new NettyHttpServerHandler());
//                .addLast(new HttpObjectAggregator(65536))
//                .addLast(new ChunkedWriteHandler())
//                .addLast(new HttpResponseEncoder(), new HttpRequestDecoder(), new NettyHttpServerHandler());
    }
}
