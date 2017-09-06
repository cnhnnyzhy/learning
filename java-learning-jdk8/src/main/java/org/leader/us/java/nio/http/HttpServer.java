package org.leader.us.java.nio.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单模拟实现一个http server
 * @author Ocean
 *
 */
public class HttpServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(80);
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Request:" + socket.toString() + "connected!");
				LineNumberReader in = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
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
				socket.close();
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
		}
	}

	private static void doResponeGet(String requestPage, Socket socket) throws IOException {
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
