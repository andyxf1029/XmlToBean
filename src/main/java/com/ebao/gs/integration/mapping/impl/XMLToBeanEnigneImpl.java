package com.ebao.gs.integration.mapping.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.IXMLToBeanEngine;
import com.ebao.gs.integration.mapping.bean.InputDataSource;
import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.mapper.MapperAgent;
import com.ebao.gs.integration.mapping.merge.MergeAgent;
import com.ebao.gs.integration.mapping.reduce.ReducerAgent;

public class XMLToBeanEnigneImpl implements IXMLToBeanEngine {

	private MapperAgent mapperAgent;

	private ReducerAgent reducerAgent;

	private MergeAgent mergeAgent;

	public void setMapperAgent(MapperAgent mapperAgent) {
		this.mapperAgent = mapperAgent;
	}

	public void setReducerAgent(ReducerAgent reducerAgent) {
		this.reducerAgent = reducerAgent;
	}

	public void setMergeAgent(MergeAgent mergeAgent) {
		this.mergeAgent = mergeAgent;
	}

	public <T> T process(InputDataSource source, Class<T> clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		List<Pair> mapperpairList = mapperAgent.processMapper(source);
		Assert.notEmpty(mapperpairList, "pair List is empty !");
		Object result = mergeAgent.merge(reducerAgent.process(mapperpairList));
		return clazz.cast(result);
	}
}
