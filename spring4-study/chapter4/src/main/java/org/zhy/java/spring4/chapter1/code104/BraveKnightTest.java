package org.zhy.java.spring4.chapter1.code104;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.zhy.java.spring4.chapter1.code103.BraveKnight;
import org.zhy.java.spring4.chapter1.code103.Quest;

public class BraveKnightTest {
	@Test
	public void knightShouldEmbarkOnQuest(){
		Quest mockQuest = mock(Quest.class);//´´½¨mock Quest
		BraveKnight knight = new BraveKnight(mockQuest);//×¢Èëmock Quest
		knight.embarkOnQuest();
		verify(mockQuest, times(1)).embark();
	}
}
