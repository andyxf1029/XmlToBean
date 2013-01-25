package com.ebao.gs.integration.mapping.mapper.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.configuration.IEngineConfigurationProvider;
import com.ebao.gs.integration.mapping.configuration.bean.MapperDefinition;
import com.ebao.gs.integration.mapping.helper.IBeanProvider;
import com.ebao.gs.integration.mapping.mapper.Mapper;
import com.ebao.gs.integration.mapping.mapper.MapperAgent;

public class MapperAgentImpl implements MapperAgent, ApplicationContextAware {

	private ApplicationContext applicationContext;

	private IEngineConfigurationProvider configurationProvider;

	private IBeanProvider beanProvider;

	public List<Pair> processMapper(InputDataSource source)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		List<Pair> pairList = new ArrayList<Pair>();
		List<MapperDefinition> mapperDefinitions = configurationProvider
				.loadMappers();
		if (mapperDefinitions != null && !mapperDefinitions.isEmpty()) {
			for (MapperDefinition mapperDefinition : configurationProvider
					.loadMappers()) {
				Mapper mapper = beanProvider.fetch(
						mapperDefinition.getClassName(), Mapper.class);
				pairList.addAll(mapper.map(source));
			}
		} else {
			pairList.addAll(this.createDefaultMapper().map(source));
		}

		return pairList;
	}

	private Mapper createDefaultMapper() {
		return (Mapper) this.applicationContext.getBean("DefaultMapper");
	}

	public void setConfigurationProvider(
			IEngineConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	public void setBeanProvider(IBeanProvider beanProvider) {
		this.beanProvider = beanProvider;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
