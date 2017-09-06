package com.zhy.java.io.nio.example1;

import java.util.HashMap;
import java.util.Map;

public class MessageCollection {
	public static Map<String, String> map;

	static {
		map = new HashMap<>();
		map.put("Hi", "Hi");
		map.put("Bye", "Bye");
		map.put("床前明月光", "疑是地上霜");
		map.put("举头望明月", "低头思故乡");
		map.put("少小离家老大回", "乡音无改鬓毛衰");
		map.put("天王盖地虎", "宝塔镇河妖");
		map.put("我是甲", "我是乙");
		map.put("我是客户端", "我是服务器");
		map.put("我是周星驰", "我是周润发");
	}
}
