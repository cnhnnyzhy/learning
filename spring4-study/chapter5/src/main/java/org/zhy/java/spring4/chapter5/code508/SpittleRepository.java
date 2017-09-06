package org.zhy.java.spring4.chapter5.code508;

import java.util.List;

public interface SpittleRepository {
	public List<Spittle> findSpittles(long max, int count);
	public Spittle findOne(long spittleId);
}
