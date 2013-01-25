package com.ebao.gs.integration.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.ebao.gs.integration.framework.cache.ICacheManager;

@Aspect
public class CacheAspect {

	private ICacheKeyBuilder keyBuilder;

	private ICacheManager cacheManager;

	@Around(value = "execution(* *(..)) && @annotation(cacheable)")
	public Object doPerfLogging(final ProceedingJoinPoint pjp,
			Cacheable cacheable) throws Throwable {
		String key = this.keyBuilder.build(pjp.getArgs());
		String region = cacheable.key();
		if (this.cacheManager.contains(region, key)) {
			return this.cacheManager.get(region, key);
		}

		Object result = pjp.proceed();
		this.cacheManager.put(region, key, result);
		return result;
	}

	public void setKeyBuilder(ICacheKeyBuilder keyBuilder) {
		this.keyBuilder = keyBuilder;
	}

	public void setCacheManager(ICacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
