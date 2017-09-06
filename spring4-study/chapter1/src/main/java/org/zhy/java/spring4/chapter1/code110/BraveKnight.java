package org.zhy.java.spring4.chapter1.code110;

import org.zhy.java.spring4.chapter1.code102.Knight;
import org.zhy.java.spring4.chapter1.code103.Quest;
import org.zhy.java.spring4.chapter1.code109.Minstrel;

public class BraveKnight implements Knight {
	private Quest quest;
	private Minstrel minstrel;
	
	public BraveKnight(Quest quest, Minstrel minstrel) {
		this.quest = quest;
		this.minstrel = minstrel;
	}

	@Override
	public void embarkOnQuest() {
		minstrel.singBeforeQuest();
		quest.embark();
		minstrel.singAfterQuest();
	}

}
