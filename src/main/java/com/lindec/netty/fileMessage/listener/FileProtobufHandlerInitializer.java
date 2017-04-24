package com.lindec.netty.fileMessage.listener;

import com.lindec.netty.fileMessage.handler.FileProtobufServerHandler;
import com.lindec.netty.fileMessage.protobuf.DBFileMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by win7 on 2017/4/20.
 * @author xuanhua.hu
 */
public class FileProtobufHandlerInitializer extends ChannelInitializer<Channel>{

    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new ProtobufEncoder());
        channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        channel.pipeline().addLast(new ProtobufDecoder(DBFileMessage.RecieveMessage.getDefaultInstance()));
        channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        channel.pipeline().addLast(new FileProtobufServerHandler());
    }
}
