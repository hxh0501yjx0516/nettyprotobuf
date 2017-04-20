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
 * Created by lindec on 2016/3/5 17:29.
 */
public class TcpClient {
    public static String HOST = "127.0.0.1";
    public static int PORT = 8999;

//    public static ServerBootstrap bootstrap = getBootstrap();
//    public static ChannelFuture channel = getChannel(HOST,PORT);
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
//        return b;
    }

//    public static final ChannelFuture getChannel(String host,int port){
//        ChannelFuture channel = null;
//        try {
//            channel = bootstrap.bind(host, port).sync();
//            channel.channel().closeFuture().sync();
//        } catch (Exception e) {
//            System.out.println("连接Server(IP[%s],PORT[%s])失败"+ host+":"+port+"----:"+e);
//            return null;
//        }finally {
//
//        }
//        return channel;
//    }

    public   void sendMsg(String msg) throws Exception {
       new TcpClient().getBootstrap("127.0.0.1",10020);
    }

    public static void main(String[] args) throws Exception {

        new TcpClient().getBootstrap("127.0.0.1",10020);

//        System.err.println(173284013/1000/1000+"    "+173085445/1000/1000  );
//        System.err.println(47844878/1000/1000+"    "+47071126 /1000/1000  );
//        System.err.println(161648043/1000/1000+"    "+161434426/1000/1000  );
    }
}
