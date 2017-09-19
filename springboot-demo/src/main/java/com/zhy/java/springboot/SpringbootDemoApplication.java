package com.zhy.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Spring Boot中通过MapperScan注解来作dao与mapper的映射
@MapperScan("com.zhy.java.springboot.dao")
@SpringBootApplication
public class SpringbootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
}
