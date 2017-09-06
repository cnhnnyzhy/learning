package com.zhy.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	private Selector selector;
	
	public void startup(){
		try {
			SocketChannel channel = SocketChannel.open();
			channel.configureBlocking(false);
			selector = Selector.open();
			channel.connect(new InetSocketAddress("localhost", 9999));
			channel.register(selector, SelectionKey.OP_CONNECT);
			System.out.println("客户端已启动……");
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen(){
		try {
			while(true){
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();
					
					if(key.isConnectable()){
						SocketChannel channel = (SocketChannel)key.channel();
						//如果正在连接，则连接完成
						if(channel.isConnectionPending()){
							channel.finishConnect();
						}
						channel.configureBlocking(false);
						channel.write(ByteBuffer.wrap(new String("客户端向服务端发送信息……").getBytes()));
						channel.register(this.selector, SelectionKey.OP_READ);
					}else if(key.isReadable()){
						read(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void read(SelectionKey key) {
		SocketChannel channel = (SocketChannel)key.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		try {
			channel.read(readBuffer);
			String msg = new String(readBuffer.array());
			System.out.println("客户端收到信息：" + msg);
			channel.write(ByteBuffer.wrap(("客户端已收到您发送的信息：" + msg).getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new NIOClient().startup();
		
	}

}
