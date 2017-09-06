package com.zhy.java.io.nio.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

public class TCPProtocolImpl implements TCPProtocol {
	
	private int bufferSize;
	
	public TCPProtocolImpl(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
		SocketChannel clientSocketChannel = serverSocketChannel.accept();
		clientSocketChannel.configureBlocking(false);
		clientSocketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {
		SocketChannel clientSocketChannel = (SocketChannel)key.channel();
		ByteBuffer buffer = (ByteBuffer)key.attachment();
		buffer.clear();
		long len = clientSocketChannel.read(buffer);
		if(len == -1){
			clientSocketChannel.close();
		}else{
			buffer.flip();
			String receiveMsg = Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
			System.out.println("接收到来自["+clientSocketChannel.socket().getRemoteSocketAddress()+"]的信息：" + receiveMsg);
			
			String sendMsg = "你好，客户端！@" + new Date().toString() + "，已经收到你的信息：" + receiveMsg;
			buffer = ByteBuffer.wrap(sendMsg.getBytes("UTF-16"));
			clientSocketChannel.write(buffer);
			
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
		
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {
		// TODO 暂时不处理

	}

}
