package org.zhy.java.spring4.chapter3.code307;

import java.util.List;

import org.zhy.java.spring4.chapter2.code201.CompactDisc;

public class BlankDisc implements CompactDisc{
	private String title;
	private String artist;
	private List<String> tracks;
	
	public BlankDisc() {
		// TODO Auto-generated constructor stub
	}
	
	public BlankDisc(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}
	
	@Override
	public void play() {
		System.out.println("Playing the " + title + " by " + artist);
	}
	
	@Override
	public void playTrack(int trackNumber) {
		System.out.println("Playing the " + tracks.get(trackNumber-1) + " by " + artist);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}
	
	
}
