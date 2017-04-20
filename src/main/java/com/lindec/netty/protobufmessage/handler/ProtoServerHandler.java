package com.lindec.netty.protobufmessage.handler;

import com.lindec.netty.protobufmessage.protobuf.DBMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by win7 on 2017/4/20.
 * @author xuanhua.hu
 */
public class ProtoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DBMessage.Message message = (DBMessage.Message)msg;
        System.err.println("protobuf消息==="+message.getMessage());
    }
}
