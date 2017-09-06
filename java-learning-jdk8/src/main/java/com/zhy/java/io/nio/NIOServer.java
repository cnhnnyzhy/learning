package com.zhy.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class NIOServer {
	private static final Logger log = Logger.getLogger(NIOServer.class);
	//服务器端口号
	private static final int PORT = 9999;
	private Selector selector;
	public void startup(){
		ServerSocketChannel channel = null;
		
		try {
			selector = Selector.open();
			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);//默认为阻塞模式，设置为非阻塞模式
			
			//绑定IP地址和端口
			channel.socket().setReuseAddress(true);
			channel.socket().bind(new InetSocketAddress(PORT));
			
			channel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务端已启动……");
			
			while(selector.select() > 0){
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();
					execute(key);
				}
			}
		} catch (IOException e) {
			log.error("NIOServer open error!", e);
		}
	}
	/**
	 * 服务器接收到请求之后处理业务逻辑
	 * @param key
	 */
	private void execute(SelectionKey key) {
		try {
			if(key.isAcceptable()){//客户端请求连接事件
				ServerSocketChannel server = (ServerSocketChannel)key.channel();
				//获得和客户端连接的通道
				SocketChannel channel = server.accept();
				//设置成非阻塞
				channel.configureBlocking(false);
				channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
				channel.register(this.selector, SelectionKey.OP_READ);
			}else if(key.isReadable()){
				read(key);
			}else if(key.isWritable()){
				
			}
		} catch (IOException e) {
			log.error("NIOServer execute error!", e);
		}
	}
	private void read(SelectionKey key) {
		SocketChannel channel = (SocketChannel)key.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		try {
			channel.read(readBuffer);
			String msg = new String(readBuffer.array());
			System.out.println("服务端收到信息：" + msg);
			channel.write(ByteBuffer.wrap(("服务端已收到您发送的信息：" + msg).getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private MyRequestVO receiveData(SocketChannel socketChannel) {
		MyRequestVO requestVO = null;
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try {
			int size = -1;
			while((size = socketChannel.read(byteBuffer)) >= 0){
				byteBuffer.flip();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		new NIOServer().startup();
	}

}
