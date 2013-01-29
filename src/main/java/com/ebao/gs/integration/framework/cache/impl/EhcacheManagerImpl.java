package com.ebao.gs.integration.framework.cache.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.InitializingBean;

import com.ebao.gs.integration.framework.cache.ICacheManager;
import com.ebao.gs.integration.framework.common.IBasePathProvider;

public class EhcacheManagerImpl implements ICacheManager, InitializingBean {

	private String configurationName;

	private IBasePathProvider basePathProvider;

	private CacheManager cacheManager;

	public void put(String region, String key, Object value) {
		if (!this.cacheManager.cacheExists(region)) {
			this.cacheManager.addCache(region);
		}

		Cache cache = this.cacheManager.getCache(region);
		cache.put(new Element(key, value));
	}

	public boolean contains(String region, String key) {
		if (!this.cacheManager.cacheExists(region)) {
			return false;
		}

		Cache cache = this.cacheManager.getCache(region);
		return cache.getKeys() != null && cache.getKeys().contains(key);
	}

	public Object get(String region, String key) {
		Cache cache = this.cacheManager.getCache(region);
		return cache.get(key).getValue();
	}

	public void afterPropertiesSet() throws Exception {
		String basePath = this.basePathProvider.getBasePath();
		this.cacheManager = CacheManager.create();

	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public void setBasePathProvider(IBasePathProvider basePathProvider) {
		this.basePathProvider = basePathProvider;
	}

}
