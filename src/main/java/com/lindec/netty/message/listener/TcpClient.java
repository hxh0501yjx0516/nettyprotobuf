package com.lindec.netty.message.listener;

import com.lindec.netty.message.handle.TcpClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

//import io.netty.channel.*;

/**
 * @author xuanha.hu
 * Created by win7 on 2017/3/30.
 */
public class TcpClient {

    /**
     * 初始化Bootstrap
     * @return
     */
    public   void  getBootstrap(String host,int port){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast( new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast( new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast( new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpClientHandler());
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture f = null;
        try {
            f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }



    public   void sendMsg(String msg) throws Exception {
       new TcpClient().getBootstrap("127.0.0.1",10020);
    }

    public static void main(String[] args) throws Exception {

        new TcpClient().getBootstrap("127.0.0.1",10020);


    }
}
