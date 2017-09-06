package com.zhy.java.jdk8.instant;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantMain {

	public static void main(String[] args) {
		System.out.println(Instant.now());
		
		System.out.println(DateTimeFormatter.ISO_TIME);
		System.out.println(Instant.MAX);
		
		try(InputStream is = new FileInputStream("a.txt");){
			
		}
	}

}
