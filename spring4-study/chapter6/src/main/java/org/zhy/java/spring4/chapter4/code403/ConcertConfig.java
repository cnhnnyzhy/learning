package org.zhy.java.spring4.chapter4.code403;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.zhy.java.spring4.chapter4.code402.Audience;

@Configuration
@EnableAspectJAutoProxy//启用AspectJ自动代理
@ComponentScan
public class ConcertConfig {
	@Bean
	public Audience audience(){
		return new Audience();
	}
}
