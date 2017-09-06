package com.zhy.java.jdk8.map;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapForeach {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		for(int i=0; i<10000000; i++){
			map.put("a" + i, "" + i);
		}
		String str = "";
		for(Entry<String, String> entry : map.entrySet()){
			str = entry.getKey() + ":" + entry.getValue();
		}
	}

}
