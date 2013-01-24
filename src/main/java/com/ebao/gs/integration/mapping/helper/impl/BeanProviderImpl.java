package com.ebao.gs.integration.mapping.helper.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.ebao.gs.integration.mapping.helper.IBeanProvider;
import com.ebao.gs.integration.mapping.utils.BeanTypeUtils;

public class BeanProviderImpl implements IBeanProvider, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public <T> T fetch(String beanName, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Assert.notNull(beanName, "beanName is Empty ");
		Assert.notNull(clazz, "clazz type  is Empty ");
		if (BeanTypeUtils.isSpringBean(beanName)) {
			return (T) applicationContext.getBean(
					this.getDetailBeanName(beanName), clazz);
		}

		return clazz.cast(Class.forName(beanName).newInstance());
	}

	private String getDetailBeanName(String beanName) {
		String[] bean = StringUtils.split(beanName, ":");
		if (bean.length > 1) {
			return bean[1];
		}

		return beanName;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
