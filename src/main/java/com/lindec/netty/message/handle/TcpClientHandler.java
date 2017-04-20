package com.lindec.netty.message.handle;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xuanha.hu
 * Created by win7 on 2017/3/30.
 */
public class TcpClientHandler extends ChannelHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) {
        // for(int i = 0 ;i<1000;i++){
             ctx.writeAndFlush("44444444");

       //  }
    }
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //messageReceived方法,名称很别扭，像是一个内部方法.
//        for (int i = 0; i < 100; i++) {
//            ctx.writeAndFlush("美丽的大中国");
//        }
//        ctx.close();
        ctx.writeAndFlush("5555555555");
        System.err.println(msg);
        ctx.close();
    }

}
