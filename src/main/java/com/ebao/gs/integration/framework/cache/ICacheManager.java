package com.ebao.gs.integration.framework.cache;

public interface ICacheManager {

	public void put(String region, String key, Object value);

	public boolean contains(String region, String key);

	public Object get(String region, String key);

}
