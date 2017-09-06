package org.zhy.java.spring4.chapter3.code307;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:org/zhy/java/spring4/chapter3/code307/app.properties")
public class ExpressiveConfig {
	@Autowired
	Environment env;
	
	@Bean
	public BlankDisc disc(){
		return new BlankDisc(env.getProperty("disc.title"), env.getProperty("disc.artist"));
	}
}
