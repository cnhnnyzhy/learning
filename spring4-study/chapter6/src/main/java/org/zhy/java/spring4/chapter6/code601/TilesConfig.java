package org.zhy.java.spring4.chapter6.code601;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[]{"/WEB-INF/layout/tiles.xml"});
		tiles.setCheckRefresh(true);//启用刷新功能
		return tiles;
	}
	
	@Bean
	public ViewResolver viewResolver(){
		return new TilesViewResolver();
	}
}
