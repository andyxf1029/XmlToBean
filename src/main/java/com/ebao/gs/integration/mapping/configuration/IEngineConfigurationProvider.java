package com.ebao.gs.integration.mapping.configuration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.configuration.bean.MapperDefinition;
import com.ebao.gs.integration.mapping.configuration.bean.MergeBean;
import com.ebao.gs.integration.mapping.configuration.bean.ReducerDefinition;

public interface IEngineConfigurationProvider {

	List<MapperDefinition> loadMappers() throws IOException, SAXException;

	MapperDefinition loadMapperByKey(String key) throws IOException,
			SAXException;

	Map<String, ReducerDefinition> loadReducers() throws IOException,
			SAXException;

	ReducerDefinition loadReducerByKey(String key) throws IOException,
			SAXException;

	MergeBean loadMerger() throws IOException, SAXException;

}
