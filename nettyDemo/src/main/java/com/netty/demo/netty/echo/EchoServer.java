package com.netty.demo.netty.echo;

import com.sun.istack.internal.FinalArrayList;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import sun.util.resources.cldr.ss.CalendarData_ss_SZ;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: EchoServer
 * Author:   lishuang
 * Date:     2019-06-13 15:39
 * Description: My Echo Server Netty Impl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class EchoServer {
    private final  int port;
    public EchoServer(int port){
        this.port = port;
    }
    public void start() throws Exception{

        // 这个group 实际上是一个线程池
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        // 第二个线程池
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossgroup, workGroup)
                    .channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoHandler());
                        }
                    });



            // Start the server.
            ChannelFuture f = bootstrap.bind(port).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        }finally {
            bossgroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static class EchoHandler extends  ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("Server received:"+in.toString(CharsetUtil.UTF_8));
            ctx.write(in);
            ctx.flush();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            //将未决消息冲刷到远程节点，并且关闭该channel
           // ctx.writeAndFlush(Unpooled.).addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) throws  Exception{
        EchoServer echoServer = new EchoServer(8000);
        echoServer.start();
    }
}
