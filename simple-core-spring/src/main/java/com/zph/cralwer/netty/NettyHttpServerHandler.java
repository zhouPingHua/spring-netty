package com.zph.cralwer.netty;

import com.zph.cralwer.netty.bean.TicketTwo;
import com.zph.cralwer.netty.handle.Route;
import com.zph.cralwer.netty.utils.MapUtils;
import com.zph.cralwer.netty.utils.RequestParser;
import com.zph.cralwer.processor.SpringProcessorRegister;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author zph  on 2018/8/6
 */
public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws IOException {
        //得到返回结果
        String result = getResult(request);
        //返回客户端消息
        writeResponse(result, ctx);
    }

    /**
     * 请求参数构建
     *
     * @param request
     * @return
     */
    private TicketTwo builderTicket(FullHttpRequest request) throws IOException {

        //请求方式 get post
        Map<String, String> parmMap = new RequestParser(request).parse();
        return MapUtils.toBean(TicketTwo.class, parmMap);
    }

    /**
     * 请求结果获取
     *
     * @return
     */
    private String getResult(FullHttpRequest request) throws IOException {

        //请求url 参数分割
        String uri = request.getUri();
        String apiUrl = uri.split("\\?")[0];
        int index = apiUrl.lastIndexOf("/");
        String url = apiUrl.substring(0, index);
        String method = apiUrl.substring(index);
        //请求参数构建
        TicketTwo ticketTwo = builderTicket(request);
        String result = "";
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(method)) {
            result = "method not found";
            return result;
        }
        //反射
        Route route = SpringProcessorRegister.routeHashMap.get(url);
        if (route == null) {
            result = "method not found";
        } else {
            result = (String) route.invoke(method, ticketTwo);
        }
        return result;
    }

    /**
     * 请求结果返回
     *
     * @param result
     * @param ctx
     * @throws UnsupportedEncodingException
     */
    private void writeResponse(String result, ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(result.getBytes("utf-8")));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //暂时只支持http
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
//        if (msg instanceof DefaultHttpRequest) {
//            handleHttpRequest(ctx, (FullHttpRequest) msg);
//        }
//
//        if (msg instanceof HttpContent) {
//            handleHttpRequest(ctx, (FullHttpRequest) msg);
//        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
