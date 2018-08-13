//package com.zph.netty.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import java.net.InetSocketAddress;
//
///**
// * @author zph  on 2018/8/8
// */
//public class MessageSendInitializeTask implements Runnable {
//
//    private EventLoopGroup eventLoopGroup = null;
//    private InetSocketAddress serverAddress = null;
//    private RpcClient loader = null;
//
//    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcClient loader) {
//        this.eventLoopGroup = eventLoopGroup;
//        this.serverAddress = serverAddress;
//        this.loader = loader;
//    }
//
//    @Override
//    public void run() {
//        Bootstrap b = new Bootstrap();
//        b.group(eventLoopGroup)
//                .channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
//        b.handler(new MessageSendChannelInitializer());
//
//        ChannelFuture channelFuture = b.connect(serverAddress);
//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
//                if (channelFuture.isSuccess()) {
//                    MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
//                    MessageSendInitializeTask.this.loader.setMessageSendHandler(handler);
//                }
//            }
//        });
//    }
//}
