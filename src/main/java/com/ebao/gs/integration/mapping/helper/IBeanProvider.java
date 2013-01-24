package com.ebao.gs.integration.mapping.helper;

public interface IBeanProvider {
	public <T> T fetch(String beanName, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException;
}
