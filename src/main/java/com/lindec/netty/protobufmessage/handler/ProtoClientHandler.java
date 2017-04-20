package com.lindec.netty.protobufmessage.handler;

import com.lindec.netty.protobufmessage.protobuf.DBMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by win7 on 2017/4/20.
 * @author xuanhua.hu
 */
public class ProtoClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        ctx.writeAndFlush(DBMessage.Message.newBuilder()
                .setMessage("小明很不错啊！！！"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
