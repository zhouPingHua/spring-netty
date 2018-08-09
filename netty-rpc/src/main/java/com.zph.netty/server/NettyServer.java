package com.zph.netty.server;

import com.zph.netty.registry.ServiceRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zph  on 2018/8/8
 */
public class NettyServer extends Thread{

    Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private String serverAddress;
    private String serviceRegistry;
    private Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public NettyServer(String serverAddress, String serviceRegistry, Map<String, Object> handlerMap){
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChildChannelInitializer(handlerMap))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);

            ChannelFuture future = bootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();
            LOGGER.debug("Server started on port {}", port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
