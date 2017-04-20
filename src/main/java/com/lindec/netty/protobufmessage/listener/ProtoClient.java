package com.lindec.netty.protobufmessage.listener;

import com.lindec.netty.protobufmessage.handler.ProtoClientHandler;
import com.lindec.netty.protobufmessage.protobuf.DBMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by win7 on 2017/4/20.
 * @author xuanhua.hu
 */
public class ProtoClient {
    public  void bind(String host,int port){
        //配置客户端
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<Channel>(){
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new ProtobufEncoder());
                        channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        channel.pipeline().addLast(new ProtobufDecoder(DBMessage.Message.getDefaultInstance()));
                        channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        channel.pipeline().addLast(new ProtoClientHandler());
                    }
                });

        try {
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        //启动client
        new ProtoClient().bind("127.0.0.1",10021);

    }
}
