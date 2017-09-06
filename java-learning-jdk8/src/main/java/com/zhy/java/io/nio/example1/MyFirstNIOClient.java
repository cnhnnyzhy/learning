package com.zhy.java.io.nio.example1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Random;

public class MyFirstNIOClient implements Runnable {

	private static final String HOST_IP = "localhost";
	private static final int PORT = 9999;
	boolean exist = false;
	int messageCount = 0;

	Random random = new Random();
	int messageSize = 0;
	String[] messageKeys = null;
	String threadName = null;
	@Override
	public void run() {
		try {
			threadName = Thread.currentThread().getName();
			messageSize = MessageCollection.map.size();
			messageKeys = MessageCollection.map.keySet().toArray(new String[messageSize]);
			Socket socket = new Socket(HOST_IP, PORT);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			new ReciverThread(is).start();
			while(!exist){
				messageCount++;
				String msg = chooseMessage();
				synchronized (is) {
					os.write(msg.getBytes("utf-8"));
					System.out.println(MessageFormat.format("[{2}][send]\t{0}:{1}", messageCount, msg, threadName));
					is.wait();
				}
				if("Bye".equalsIgnoreCase(msg)){
					break;
				}
			}
			is.close();
			os.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
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
		private InputStream is;
		
		public ReciverThread(InputStream is) {
			this.is = is;
		}

		@Override
		public void run() {
			try {
				String line = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
				while((line = reader.readLine()) != null){
					synchronized (is) {
						//接到服务器的消息，通知下主线程
						is.notify();
					}
					System.out.println(MessageFormat.format("[{1}][Recived]:{0}", line, threadName));
					if("Bye".equals(line.trim())){
						exist = true;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

