package com.lindec.netty.message.handle;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by lindec on 2016/3/5 17:21.
 */
public class TcpServerHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        System.out.println("Unexpected exception from downstream."+cause);
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("SERVER接收到消息:"+msg);
        ctx.writeAndFlush("yes, server is accepted you ,nice !"+"亲爱的");
    }
}