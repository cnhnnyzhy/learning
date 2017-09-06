package org.leader.us.java.nio.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyHttpServer {
	/**服务器默认端口*/
	public static final int DEFAULT_PORT = 8888;
	public final int port;
	private static final int MAX_POOL_SIZE = 8;
	private static final int CORE_POOL_SIZE = 1;
	private static final long KEEP_ALIVE_TIME = 0L;
	
	private ServerSocket serverSocket = null;
	private boolean running = true;
	private ExecutorService threadPool;
	
	public MyHttpServer() {
		this(DEFAULT_PORT);
	}
	
	public MyHttpServer(int port) {
		this.port = port;
	}
	
	public void startup(){
		try {
			serverSocket = new ServerSocket();
			System.out.println("Starting MyHttpServer at 127.0.0.1:" + this.port);
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(this.port));
			threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

			while(running){
				Socket socket;
				try {
					socket = serverSocket.accept();
					threadPool.submit(()->{
						LineNumberReader in = null;
						try {
							System.out.println(Thread.currentThread().getName() + ":[Request:" + socket.toString() + "connected!]");
							in = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
							String lineInput;
							String requestPage = null;
							while((lineInput = in.readLine()) != null){
								System.out.println(lineInput);
								if(in.getLineNumber() == 1){
									requestPage = lineInput.substring(lineInput.indexOf('/') + 1, lineInput.lastIndexOf(' '));
									System.out.println("Request Page: " + requestPage);
								}else{
									if(lineInput.isEmpty()){
										System.out.println("Header finished!");
										doResponeGet(requestPage, socket);
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							if(in != null){
								try {
									in.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if(socket != null){
								try {
									socket.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(threadPool != null){
				threadPool.shutdown();
			}
		}
		System.out.println("MyHttpServer shutdown!");
	}
	
	private void doResponeGet(String requestPage, Socket socket) {
		final String WEB_ROOT = "d:";
		File theFile = new File(WEB_ROOT, requestPage);
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
			if(theFile.exists()){
				InputStream fileIn = new FileInputStream(theFile);
				byte[] buf = new byte[fileIn.available()];
				fileIn.read(buf);
				fileIn.close();
				out.write(buf);
				out.flush();
				System.out.println("Request complete.");
			}else{
				String msg = "I can't find bao zang...cry..\r\n";
				String response = "HTTP/1.1 200 OK\r\n";
				response += "Server:ZHY Server/0.1\r\n";
				response += "Content-Length:" + (msg.length() - 4) + "\r\n";
				response += "\r\n";
				response += msg;
				out.write(response.getBytes());
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	public static void main(String[] args) {
		new MyHttpServer().startup();
	}

}
