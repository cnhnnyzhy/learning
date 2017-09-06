package org.zhy.java.spring4.chapter4.code408;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhy.java.spring4.chapter2.code201.CompactDisc;
import org.zhy.java.spring4.chapter4.code406.TrackCounter;
import org.zhy.java.spring4.chapter4.code407.TrackCounterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCounterTest {
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Autowired
	CompactDisc cd;
	
	@Autowired
	TrackCounter counter;
	
	@Test
	public void testTrackCounter(){
		cd.playTrack(1);
		cd.playTrack(2);
		cd.playTrack(3);
		cd.playTrack(3);
		cd.playTrack(3);
		cd.playTrack(3);
		
		cd.playTrack(4);
		cd.playTrack(4);
		
		assertEquals(1, counter.getPlayCount(1));
		assertEquals(1, counter.getPlayCount(2));
		assertEquals(4, counter.getPlayCount(3));
		assertEquals(2, counter.getPlayCount(4));
		assertEquals(0, counter.getPlayCount(5));
		assertEquals(0, counter.getPlayCount(6));
		assertEquals(0, counter.getPlayCount(7));
	}
}
