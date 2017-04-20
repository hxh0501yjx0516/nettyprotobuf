package com.lindec.netty.protobufmessage.listener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by win7 on 2017/4/20.
 * @author xuanhua.hu
 */
public class ProtoServer {
    public void bind(int port) {
        NioEventLoopGroup bossgroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
        NioEventLoopGroup workergroup = new NioEventLoopGroup(4);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossgroup, workergroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ProtoServerHandlerInitializer());
        try {
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new ProtoServer().bind(10021);
    }
}
