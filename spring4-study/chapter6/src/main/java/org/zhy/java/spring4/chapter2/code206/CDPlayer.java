package org.zhy.java.spring4.chapter2.code206;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhy.java.spring4.chapter2.code201.CompactDisc;
@Component
public class CDPlayer implements MediaPlayer {
	private CompactDisc cd;
	@Autowired
	public CDPlayer(CompactDisc cd){
		this.cd = cd;
	}
	/*@Autowired
	public void setCompactDisc(CompactDisc cd){
		this.cd = cd;
	}*/
	public void play() {
		cd.play();
	}

}
