package org.zhy.java.spring4.chapter1.code107;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhy.java.spring4.chapter1.code102.Knight;
import org.zhy.java.spring4.chapter1.code103.BraveKnight;
import org.zhy.java.spring4.chapter1.code103.Quest;
import org.zhy.java.spring4.chapter1.code105.SlayDragonQuest;

@Configuration
public class KnightConfig {
	@Bean
	public Knight knight(){
		return new BraveKnight(quest());
	}
	@Bean
	public Quest quest() {
		return new SlayDragonQuest(System.out);
	}
}
