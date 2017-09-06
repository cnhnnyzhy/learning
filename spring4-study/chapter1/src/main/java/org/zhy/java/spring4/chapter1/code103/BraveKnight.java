package org.zhy.java.spring4.chapter1.code103;

import org.zhy.java.spring4.chapter1.code102.Knight;

public class BraveKnight implements Knight {
	private Quest quest;
	
	public BraveKnight(Quest quest) {//Quest��ע�����
		this.quest = quest;
	}
	
	@Override
	public void embarkOnQuest() {
		quest.embark();
	}
}
