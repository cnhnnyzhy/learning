package org.zhy.java.spring4.chapter5.code516;

import java.util.List;

public interface SpitterRepository {
	public List<Spitter> findSpitters(long max, int count);
	public Spitter findOne(long spitterId);
	public Spitter findByUserName(String userName);
	public Spitter save(Spitter spitter);
}
