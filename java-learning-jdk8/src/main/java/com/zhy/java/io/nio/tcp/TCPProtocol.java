package com.zhy.java.io.nio.tcp;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface TCPProtocol {
	/**
	 * 处理accpept请求
	 * @param key
	 * @throws IOException
	 */
	void handleAccept(SelectionKey key)throws IOException;
	/**
	 * 处理read请求
	 * @param key
	 * @throws IOException
	 */
	void handleRead(SelectionKey key)throws IOException;
	/**
	 * 处理write请求
	 * @param key
	 * @throws IOException
	 */
	void handleWrite(SelectionKey key)throws IOException;
}
