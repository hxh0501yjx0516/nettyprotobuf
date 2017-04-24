package com.lindec.netty.fileMessage.listener;

import com.lindec.netty.fileMessage.handler.FileProtobufServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by win7 on 2017/4/20.
 * @author  xuanhua.hu
 */
public class FileProtobufServer {
    public  void bin(int port){
        NioEventLoopGroup bossgroup = new NioEventLoopGroup();
        NioEventLoopGroup workdergroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossgroup, workdergroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new FileProtobufHandlerInitializer());
        try {
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossgroup.shutdownGracefully();
            workdergroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        System.err.println("服务已启动");
        new FileProtobufServer().bin(20010);
    }
}
