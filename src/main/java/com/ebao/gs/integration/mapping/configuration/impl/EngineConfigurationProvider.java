package com.ebao.gs.integration.mapping.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester3.Digester;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.framework.aop.Cacheable;
import com.ebao.gs.integration.mapping.configuration.IEngineConfigurationProvider;
import com.ebao.gs.integration.mapping.configuration.bean.MapAndReducerBean;
import com.ebao.gs.integration.mapping.configuration.bean.MapperDefinition;
import com.ebao.gs.integration.mapping.configuration.bean.MergeBean;
import com.ebao.gs.integration.mapping.configuration.bean.ReducerDefinition;

public class EngineConfigurationProvider implements
		IEngineConfigurationProvider {

	private String mapReducePath;

	public List<MapperDefinition> loadMappers() throws IOException,
			SAXException {
		return this.loadAllConfigurationFile().getMapperList();
	}

	public MapperDefinition loadMapperByKey(String key) throws IOException,
			SAXException {
		Assert.notNull(key, "loadMapperByKey key is null");
		for (MapperDefinition mapperDefinition : this
				.loadAllConfigurationFile().getMapperList()) {
			if (key.equals(mapperDefinition.getId())) {
				return mapperDefinition;
			}
		}

		return null;
	}

	public Map<String, ReducerDefinition> loadReducers() throws IOException,
			SAXException {
		Map<String, ReducerDefinition> resultMap = new HashMap<String, ReducerDefinition>();
		for (ReducerDefinition reducerDefinition : this
				.loadAllConfigurationFile().getReducerList()) {
			resultMap.put(reducerDefinition.getId(), reducerDefinition);
		}

		return resultMap;
	}

	public ReducerDefinition loadReducerByKey(String key) throws IOException,
			SAXException {
		Assert.notNull(key, "loadReducerByKey key is null");
		for (ReducerDefinition reducerDefinition : this
				.loadAllConfigurationFile().getReducerList()) {
			if (key.equals(reducerDefinition.getId())) {
				return reducerDefinition;
			}
		}
		return null;
	}

	public MergeBean loadMerger() throws IOException, SAXException {
		return this.loadAllConfigurationFile().getMerge();
	}

	@Cacheable(key = "mapAndReduce")
	public MapAndReducerBean loadAllConfigurationFile() throws IOException,
			SAXException {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("mapping-struture", MapAndReducerBean.class);
		digester.addObjectCreate("mapping-struture/mappers/mapper",
				MapperDefinition.class);
		digester.addObjectCreate("mapping-struture/reducers/reducer",
				ReducerDefinition.class);
		digester.addObjectCreate("mapping-struture/mergers/merger",
				MergeBean.class);
		digester.addSetProperties("mapping-struture/mappers/mapper", "id", "id");
		digester.addSetProperties("mapping-struture/reducers/reducer", "id",
				"id");
		digester.addSetProperties("mapping-struture/mergers/merger", "id", "id");
		digester.addSetProperties("mapping-struture/mappers/mapper", "class",
				"className");
		digester.addSetProperties("mapping-struture/reducers/reducer", "class",
				"className");
		digester.addBeanPropertySetter(
				"mapping-struture/reducers/reducer/rulePath", "rulePath");
		digester.addSetProperties("mapping-struture/mergers/merger", "class",
				"className");
		digester.addSetNext("mapping-struture/mappers/mapper", "addMapper");
		digester.addSetNext("mapping-struture/reducers/reducer", "addReducer");
		digester.addSetNext("mapping-struture/mergers/merger", "setMerger");
		MapAndReducerBean bean = digester.parse(new File(
				"resource/map_reduce.xml"));
		return bean;

	}

	public void setMapReducePath(String mapReducePath) {
		this.mapReducePath = mapReducePath;
	}

}
