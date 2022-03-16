package com.qiang.io.netty;

import com.qiang.io.ResponseWriter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liq
 * @date 2021/7/9 16:08
 */
public class HelloHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接上服务器");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端的方法");
        ctx.writeAndFlush(ResponseWriter.buildResponse());
    }
}
