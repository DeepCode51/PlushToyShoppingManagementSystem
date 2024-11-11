package com.soft.admin.dao;

import java.util.List;
import com.soft.admin.domain.Cache;

public interface ICacheDao {

	public abstract int addCache(Cache cache);

	public abstract int delCache(int cache_id);
	
	public abstract int delCacheByUserId(int user_id);

	public abstract int delCaches(String[] cache_ids);

	public abstract int updateCache(Cache cache);

	public abstract Cache getCache(Cache cache);

	public abstract List<Cache>  listCaches(Cache cache);

	public abstract int listCachesCount(Cache cache);

}
