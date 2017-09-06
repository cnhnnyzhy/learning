package org.zhy.java.spring4.chapter1.code102;

public class DamselRescuingKnight implements Knight{
	private RescueDamselQuest quest;
	public DamselRescuingKnight() {
		quest = new RescueDamselQuest();//此处与RescueDamselQuest紧耦合
	}
	@Override
	public void embarkOnQuest() {
		quest.embark();
	}
}
