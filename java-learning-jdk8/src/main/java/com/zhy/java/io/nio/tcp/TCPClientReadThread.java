package com.zhy.java.io.nio.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class TCPClientReadThread implements Runnable {
	
	private Selector selector;
	
	public TCPClientReadThread(Selector selector) {
		this.selector = selector;
	}

	@Override
	public void run() {
		try {
			while(selector.select() > 0){
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					if(selectionKey.isReadable()){
						SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						socketChannel.read(buffer);
						buffer.flip();
						String receivedMsg = Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
						System.out.println("接收到来自服务器["+socketChannel.socket().getRemoteSocketAddress()+"]的消息：" + receivedMsg);
						selectionKey.interestOps(SelectionKey.OP_READ);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
