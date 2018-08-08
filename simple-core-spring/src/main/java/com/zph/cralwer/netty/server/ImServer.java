package com.zph.cralwer.netty.server;

import com.zph.cralwer.netty.client.ClientPoHandler;
import com.zph.cralwer.netty.serializable.kryo.KryoDecoder;
import com.zph.cralwer.netty.serializable.kryo.KryoEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author zph  on 2018/8/6
 */
public class ImServer {

    public void run(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //实体类传输数据，Kryo序列化
                        ch.pipeline().addLast("decoder", new KryoEncoder());
                        ch.pipeline().addLast("encoder", new KryoDecoder());
                        ch.pipeline().addLast(new ClientPoHandler());
                        //实体类传输数据，base64序列化,base64的使用需要在String的基础上，不然消息是无法直接传递。
//                    ch.pipeline().addLast("decoder", new MessageDecoder());
//                    ch.pipeline().addLast("encoder", new MessageEncoder());
//                    ch.pipeline().addLast("base64Decoder", new Base64Decoder());
//                    ch.pipeline().addLast("base64Encoder", new Base64Encoder());
//                    ch.pipeline().addLast(new ClientPoHandler());
                        //实体类传输数据，jdk序列化
//                    ch.pipeline().addLast("decoder", new MessageDecoder());
//                    ch.pipeline().addLast("encoder", new MessageEncoder());
//                    ch.pipeline().addLast(new ClientPoHandler());
                        //字符串传输数据
//                    ch.pipeline().addLast("decoder", new StringDecoder());
//                    ch.pipeline().addLast("encoder", new StringEncoder());
//                    ch.pipeline().addLast(new ClientStringHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
