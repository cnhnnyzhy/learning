package org.leader.us.java.nio.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池简单模拟实现一个http server
 * @author Ocean
 *
 */
public class ThreadPoolHttpServer {
	
	private static final int PORT = 8000;
	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private static final int POOL_SIZE = 10;
	
	
	public ThreadPoolHttpServer() {
		
	}
	
	public void start() throws IOException{
		serverSocket = new ServerSocket(PORT);
		threadPool = new ThreadPoolExecutor(1, 10, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		System.out.println("服务器已启动");
		while(true){
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				threadPool.execute(new Handler(socket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		try {
//			new ThreadPoolHttpServer().start();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		return;
//		
		ServerSocket serverSocket = null;
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		try {
			serverSocket = new ServerSocket(8880);
			while(true){
				final Socket socket = serverSocket.accept();
				threadPool.execute(() -> {
					LineNumberReader in = null;
					OutputStream out = null;
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
									//doResponeGet(requestPage, socket);
									
									final String WEB_ROOT = "d:";
									File theFile = new File(WEB_ROOT, requestPage);
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
								}
							}
						}
						System.out.println(Thread.currentThread().getName());
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
						if(out != null){
							try {
								out.close();
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
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			threadPool.shutdown();
		}
	}

	private void doResponeGet(String requestPage, Socket socket) throws IOException {
		final String WEB_ROOT = "d:";
		File theFile = new File(WEB_ROOT, requestPage);
		OutputStream out = socket.getOutputStream();
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
	}

}

class Handler implements Runnable{
	public static final String CHARCODE = "utf-8";
	private Socket socket;
	public Handler(Socket socket) {
		this.socket = socket;
	}
	
	private PrintWriter getWriter(Socket socket) throws IOException{
		OutputStream out = socket.getOutputStream();
		return new PrintWriter(out, true);
	}
	
	private BufferedReader getReader(Socket socket) throws IOException{
		InputStream in = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(in));
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter out = null;
		try {
			br = getReader(socket);
			out = getWriter(socket);
			String msg = null;
			while((msg = br.readLine()) != null){
				System.out.println(msg);
				out.println("a");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				out.close();
			}
		}
	}
}
