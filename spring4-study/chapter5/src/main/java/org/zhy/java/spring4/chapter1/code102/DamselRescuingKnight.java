package org.zhy.java.spring4.chapter1.code102;

public class DamselRescuingKnight implements Knight{
	private RescueDamselQuest quest;
	public DamselRescuingKnight() {
		quest = new RescueDamselQuest();//�˴���RescueDamselQuest�����
	}
	@Override
	public void embarkOnQuest() {
		quest.embark();
	}
}
