package org.zhy.java.spring4.chapter2.code202;

import org.springframework.stereotype.Component;
import org.zhy.java.spring4.chapter2.code201.CompactDisc;
@Component
public class SgtPeppers implements CompactDisc {
	private String title = "Sgt . Pepper's Lonely Hearts Club Band";
	private String artist = "The Beatles";
	public void play() {
		System.out.println("Playing the " + title + " by " + artist);
	}
	@Override
	public void playTrack(int trackNumber) {
		
	}
}
