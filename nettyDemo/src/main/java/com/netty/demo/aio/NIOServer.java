package com.netty.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: NIOServer
 * Author:   lishuang
 * Date:     2019-06-13 19:22
 * Description: NIO Server
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class NIOServer {
    public static void main(String[] args) throws  Exception {

        ServerSocketChannel nioServerSocketChannel  = ServerSocketChannel.open();
        nioServerSocketChannel.socket().bind(new InetSocketAddress(9999));
        nioServerSocketChannel.configureBlocking(false);//非阻塞模式
        //创建一个 Selector 用于轮询是否有事件发生
        Selector selector = Selector.open();



    }
}
