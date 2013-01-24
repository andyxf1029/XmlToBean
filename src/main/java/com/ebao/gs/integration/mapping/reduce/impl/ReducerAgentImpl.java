package com.ebao.gs.integration.mapping.reduce.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.perf4j.aop.Profiled;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.helper.IReducerCreater;
import com.ebao.gs.integration.mapping.reduce.ReducerAgent;

public class ReducerAgentImpl implements ReducerAgent {

	private IReducerCreater reducerCreater;

	public void setReducerCreater(IReducerCreater reducerCreater) {
		this.reducerCreater = reducerCreater;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ebao.gs.integration.mapping.configuration.reduce.ReducerAgent#process
	 * (java.util.List)
	 */
	
	@Profiled(message="ReducerAgentImpl")
	public Map<String, Object> process(List<Pair> pairs)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> contextMap = createContextMap();
		for (Pair pair : pairs) {
			resultMap.putAll(reducerCreater.createReducer(pair.getKey())
					.reduce(pair, contextMap));
		}

		return resultMap;
	}

	/**
	 * if concurrent run , pls change to ConcurrentMap
	 * 
	 * @return
	 */
	private Map<String, Object> createContextMap() {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		return contextMap;
	}

}
