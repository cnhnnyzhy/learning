package com.zhy.java.io.nio.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.MessageFormat;
import java.util.Iterator;

public class MyFirstNIOServer {
	private static final String HOST_IP = "127.0.0.1";
	private static final int PORT = 9999;
	protected Selector selector;
	protected Charset charset = Charset.forName("utf-8");
	protected CharsetEncoder charsetEncoder = charset.newEncoder();
	protected CharsetDecoder charsetDecoder = charset.newDecoder();
	private int clientCount;
	
	
	public void startup(){
		try {
			selector = Selector.open();
			ServerSocketChannel server = ServerSocketChannel.open();
			server.socket().bind(new InetSocketAddress(PORT));
			server.configureBlocking(false);
			server.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println(MessageFormat.format("Server[{0}:{1}] started.Waiting for clients.", HOST_IP, String.valueOf(PORT)));
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listen() throws IOException{
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				execute(key);
			}
		}
	}

	private void execute(SelectionKey key) throws IOException {
		if(key.isAcceptable()){
			clientCount++;
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
			SocketChannel socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			Socket socket = socketChannel.socket();
			
			//注册一个READ的KEY接收客户端的消息
			SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ);
			readKey.attach("第"+clientCount+"个客户端["+socket.getRemoteSocketAddress()+"]：");
			System.out.println(MessageFormat.format("{0}\t[connected]...", readKey.attachment()));
		}else if(key.isReadable()){
			//有消息进来
			ByteBuffer buffer = ByteBuffer.allocate(100);
			SocketChannel socketChannel = (SocketChannel)key.channel();
			try {
				int len = socketChannel.read(buffer);
				if(len > 0){
					//将缓冲区切换为读模式
					buffer.flip();
					String msg = charsetDecoder.decode(buffer).toString();
					String replyMsg = MessageCollection.map.get(msg);
					if(replyMsg == null){
						replyMsg = "Sorry! I don't understand your message.";
					}
					socketChannel.write(charsetEncoder.encode(CharBuffer.wrap(replyMsg + "\n")));
					System.out.println(MessageFormat.format("{0}\t[recieved]:{1}----->\t[send]:{2}", key.attachment(), msg, replyMsg));
					key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
				}else{
					//输入结束，关闭socketChannel
					System.out.println(MessageFormat.format("{0} read finish! Close socketChannel!", key.attachment()));
					socketChannel.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(MessageFormat.format("{0} socket close!", key.attachment()));
				socketChannel.close();
			}
		}else if(key.isWritable()){
			System.out.println(MessageFormat.format("{0} TODO: isWritable()????????????????", key.attachment()));
		}else if(key.isConnectable()){
			System.out.println(MessageFormat.format("{0} TODO: isConnectable()????????????????", key.attachment()));
		}else{
			System.out.println(MessageFormat.format("{0} TODO: else????????????????", key.attachment()));
		}
	}

	public static void main(String[] args) {
		new MyFirstNIOServer().startup();
	}

}
