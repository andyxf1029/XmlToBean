package com.ebao.gs.integration.mapping.aop;

public class CacheKeyDefaultBuilder implements ICacheKeyBuilder {

	public String build(Object[] args) {
		StringBuilder key = new StringBuilder();
		for (Object object : args) {
			key.append(object.toString());
		}

		return key.toString();
	}

}
