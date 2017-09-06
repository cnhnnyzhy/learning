package org.zhy.java.spring4.chapter1.code105;

import java.io.PrintStream;

import org.zhy.java.spring4.chapter1.code103.Quest;

public class SlayDragonQuest implements Quest {
	private PrintStream stream;
	
	public SlayDragonQuest(PrintStream stream) {
		this.stream = stream;
	}
	
	@Override
	public void embark() {
		stream.println("Enbarking on quest to slay the dragon!");
	}

}
