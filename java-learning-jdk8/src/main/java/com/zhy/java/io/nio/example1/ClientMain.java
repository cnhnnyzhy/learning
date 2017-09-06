package com.zhy.java.io.nio.example1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ClientMain {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		IntStream.range(0, 1).forEach(i -> {
			//threadPool.execute(new MyFirstNIOClient());
			threadPool.execute(new MySecondNIOClient());
		});
		threadPool.shutdown();
	}

}
