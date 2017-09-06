package com.zhy.java.io.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.stream.IntStream;

public class TCPClient {
	
	private Selector selector;
	private SocketChannel socketChannel;
	
	private String hostIP;
	private int port;
	
	

	public TCPClient(String hostIP, int port) {
		this.hostIP = hostIP;
		this.port = port;
		
		init();
	}



	private void init() {
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open(new InetSocketAddress(hostIP, port));
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			//启动读取线程
			new Thread(new TCPClientReadThread(selector)).start();;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) throws IOException{
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes("UTF-16"));
		socketChannel.write(buffer);
	}



	public static void main(String[] args) {
		TCPClient client = new TCPClient("localhost", 9999);
		StringBuffer sb = new StringBuffer();
		IntStream.range(0, 1000).forEach(i -> {
			sb.append(i).append(",");
		});
		try {
			client.sendMsg(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
