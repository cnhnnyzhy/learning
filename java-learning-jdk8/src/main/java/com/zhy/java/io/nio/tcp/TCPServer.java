package com.zhy.java.io.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class TCPServer {
	
	private static final int BUFFER_SIZE = 1024;
	private static final int TIME_OUT = 3000;
	private static final int PORT = 9999;
	
	private void startup(){
		try {
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(PORT));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("TCPServer started!");
			TCPProtocol protocol = new TCPProtocolImpl(BUFFER_SIZE);
			while(true){
				if(selector.select(TIME_OUT) == 0){
					continue;
				}
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					
					if(selectionKey.isAcceptable()){
						protocol.handleAccept(selectionKey);
					}else if(selectionKey.isReadable()){
						protocol.handleRead(selectionKey);
					}else if(selectionKey.isValid() && selectionKey.isWritable()){
						protocol.handleWrite(selectionKey);
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TCPServer().startup();
	}

}
