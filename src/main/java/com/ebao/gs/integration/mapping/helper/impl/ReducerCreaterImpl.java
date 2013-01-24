package com.ebao.gs.integration.mapping.helper.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.configuration.IEngineConfigurationProvider;
import com.ebao.gs.integration.mapping.configuration.bean.ReducerDefinition;
import com.ebao.gs.integration.mapping.helper.IBeanProvider;
import com.ebao.gs.integration.mapping.helper.IReducerCreater;
import com.ebao.gs.integration.mapping.reduce.Reducer;

public class ReducerCreaterImpl implements IReducerCreater,
		ApplicationContextAware {

	private static final String DEFAULT_REDUCER = "DefaultReducer";

	private ApplicationContext applicationContext;

	private IEngineConfigurationProvider configurationProvider;

	private IBeanProvider beanFetcher;

	public Reducer createReducer(String key) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException,
			SAXException {
		Map<String, ReducerDefinition> reduceMap = configurationProvider
				.loadReducers();

		if (reduceMap.containsKey(key)) {
			return Reducer.class.cast(beanFetcher.fetch(reduceMap.get(key)
					.getClassName(), Reducer.class));
		} else {
			return (Reducer) applicationContext.getBean(DEFAULT_REDUCER);
		}

	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setConfigurationProvider(
			IEngineConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	public void setBeanFetcher(IBeanProvider beanFetcher) {
		this.beanFetcher = beanFetcher;
	}

}
