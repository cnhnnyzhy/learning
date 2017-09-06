package com.zhy.java.io.nio.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MySecondNIOClient implements Runnable {

	private static final String HOST_IP = "localhost";
	private static final int PORT = 9999;
	boolean exist = false;
	int messageCount = 0;

	Random random = new Random();
	int messageSize = 0;
	String[] messageKeys = null;
	String threadName = null;
	
	private Selector selector;
	private SocketChannel socketChannel;
	
	protected Charset charset = Charset.forName("utf-8");
	protected CharsetEncoder charsetEncoder = charset.newEncoder();
	protected CharsetDecoder charsetDecoder = charset.newDecoder();
	
	Object lock = new Object();
	
	@Override
	public void run() {
		try {
			threadName = Thread.currentThread().getName();
			messageSize = MessageCollection.map.size();
			messageKeys = MessageCollection.map.keySet().toArray(new String[messageSize]);
			
			selector = Selector.open();
			socketChannel = SocketChannel.open(new InetSocketAddress(HOST_IP, PORT));
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			
			
			new ReciverThread().start();
			while(!exist){
				TimeUnit.SECONDS.sleep(random.nextInt(10));
				messageCount++;
				String msg = chooseMessage();
				synchronized (lock) {
					socketChannel.write(charset.encode(msg));
					System.out.println(MessageFormat.format("[{2}][send]\t{0}:{1}", messageCount, msg, threadName));
					lock.wait();
				}
				if("Bye".equalsIgnoreCase(msg)){
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socketChannel.close();
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String chooseMessage(){
		int index = random.nextInt(messageSize);
		String msg = messageKeys[index];
		if(messageCount < 10 && "Bye".equalsIgnoreCase(msg)){
			return chooseMessage();
		}
		return msg;
	}
	
	class ReciverThread extends Thread{

		@Override
		public void run() {
			try {
				while(selector.select() > 0){
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while(iterator.hasNext()){
						SelectionKey selectionKey = iterator.next();
						iterator.remove();
						if(selectionKey.isReadable()){
							SocketChannel channel = (SocketChannel)selectionKey.channel();
							String msg = "";
							ByteBuffer readBuffer = ByteBuffer.allocate(1024);
							while(channel.read(readBuffer) > 0){
								readBuffer.flip();
								msg += charsetDecoder.decode(readBuffer).toString();
								readBuffer.clear();
							}
							synchronized (lock) {
								//接到服务器的消息，通知下主线程
								lock.notify();
							}
							System.out.println(MessageFormat.format("[{1}][Recived]:{0}", msg, threadName));
							if("Bye".equals(msg.trim())){
								exist = true;
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

