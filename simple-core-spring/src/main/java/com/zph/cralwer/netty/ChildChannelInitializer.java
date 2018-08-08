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
                .addLast("http-aggregator", new HttpObjectAggregator(65536))
                .addLast(new ChunkedWriteHandler())
                .addLast("action-handler", new NettyHttpServerHandler());
//                .addLast(new HttpObjectAggregator(65536))
//                .addLast(new ChunkedWriteHandler())
//                .addLast(new HttpResponseEncoder(), new HttpRequestDecoder(), new NettyHttpServerHandler());
    }
}
