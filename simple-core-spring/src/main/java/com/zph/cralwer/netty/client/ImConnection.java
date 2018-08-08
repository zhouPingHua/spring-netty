package com.zph.cralwer.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author zph  on 2018/8/6
 */
public class ImConnection {
    private Channel channel;
    public Channel connect(String host, int port) {
        doConnect(host, port);
        return this.channel;
    }
    private void doConnect(String host, int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //实体类传输数据，jdk序列化
                    ch.pipeline().addLast("decoder", new HttpResponseEncoder());
                    ch.pipeline().addLast("encoder", new HttpRequestDecoder());
                    ch.pipeline().addLast(new ClientPoHandler());
                    //字符串传输数据
                    /*ch.pipeline().addLast("decoder", new StringDecoder());
                    ch.pipeline().addLast("encoder", new StringEncoder());
                    ch.pipeline().addLast(new ClientStringHandler());*/
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            channel = f.channel();
            channel.write("5555555555");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ImConnection imConnection = new ImConnection();
        imConnection.connect("127.0.0.1",8888);
    }
}