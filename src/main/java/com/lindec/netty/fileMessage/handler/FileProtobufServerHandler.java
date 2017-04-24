package com.lindec.netty.fileMessage.handler;


import com.lindec.netty.fileMessage.protobuf.DBFileMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by win7 on 2017/4/20.
 * @author  xaunhua.hu
 */
public class FileProtobufServerHandler  extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DBFileMessage.RecieveMessage message = (DBFileMessage.RecieveMessage) msg;
        System.err.println(message.getMsgType()+"    "+message.getData());
    }
}
