package org.zhy.java.spring4.chapter4.code407;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.zhy.java.spring4.chapter2.code201.CompactDisc;
import org.zhy.java.spring4.chapter3.code307.BlankDisc;
import org.zhy.java.spring4.chapter4.code406.TrackCounter;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {
	@Bean
	public CompactDisc sgtPeppers(){
		BlankDisc cd = new BlankDisc();
		cd.setTitle("Sgt . Pepper's Lonely Hearts Club Band");
		cd.setArtist("The Beatles");
		
		List<String> tracks = new ArrayList<>();
		tracks.add("Sgt . Pepper's Lonely Hearts Club Band");
		tracks.add("With a little help from my friends");
		tracks.add("Lucy in the Sky with Diamonds");
		tracks.add("Getting Better");
		tracks.add("Fixing a Hole");
		cd.setTracks(tracks);
		return cd;
	}
	
	@Bean
	public TrackCounter trackCounter(){
		return new TrackCounter();
	}
}
